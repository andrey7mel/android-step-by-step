package com.andrey7mel.testrx.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.model.data.Repo;
import com.andrey7mel.testrx.presenter.IPresenter;
import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.presenter.filters.RepoListFilter;
import com.andrey7mel.testrx.view.adapters.RecyclerViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.editText)
    EditText editText;

    @Bind(R.id.button)
    Button searchButton;

    private RecyclerViewAdapter adapter;

    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        presenter = new RepoListPresenter(this);
        presenter.setFilter(new RepoListFilter(editText.getText().toString()));
        presenter.loadData();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    presenter.setFilter(new RepoListFilter(text));
                    presenter.loadData();
                }

            }
        });

    }

    @Override
    public void inflateData(List<Repo> list) {
        if (list != null && !list.isEmpty()) {
            adapter.setRepos(list);
        } else {
            makeToast(getString(R.string.empty_repo_list));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.unsubscribe();
        }
    }

    private void makeToast(String text) {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(Throwable e) {
        makeToast(e.getMessage());
    }
}
