package com.andrey7mel.testrx.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.presenter.BasePresenterImpl;
import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.presenter.vo.Repository;
import com.andrey7mel.testrx.view.ActivityCallback;
import com.andrey7mel.testrx.view.adapters.RepoListAdapterNew;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoListFragment extends BaseFragment implements RepoListView {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.edit_text)
    EditText editText;
    @Bind(R.id.button_search)
    Button searchButton;
    private RepoListPresenter presenter = new RepoListPresenter(this);
    private RepoListAdapterNew adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new RepoListAdapterNew(new ArrayList<>(), presenter);
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> presenter.onSearchButtonClick());

        presenter.onCreate(savedInstanceState);

        return view;
    }


    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenterImpl getPresenter() {
        return presenter;
    }


    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showRepoList(List<Repository> repoList) {
        adapter.setRepoList(repoList);
    }

    @Override
    public void startRepoInfoFragment(Repository repository) {
        ((ActivityCallback) getActivity()).startRepoInfoFragment(repository);
    }

    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }

    @Override
    public String getUserName() {
        return editText.getText().toString();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

}
