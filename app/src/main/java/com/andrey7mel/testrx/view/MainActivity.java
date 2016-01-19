package com.andrey7mel.testrx.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.model.data.Repo;
import com.andrey7mel.testrx.presenter.Presenter;
import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.view.adapters.RecyclerViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.editText)
    EditText editText;

    @Bind(R.id.button)
    Button searchButton;

    private RecyclerViewAdapter adapter;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        presenter = new RepoListPresenter(this);
        presenter.onSearchButtonClick();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.onSearchButtonClick();
            }
        });

    }

    @Override
    public void showData(List<Repo> list) {
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
            presenter.onStop();
        }
    }

    private void makeToast(String text) {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(Throwable e) {
        makeToast(e.getMessage());
    }

    @Override
    public String getUserName() {
        return editText.getText().toString();
    }
}
