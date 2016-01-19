package com.andrey7mel.testrx.view.fragments;

import com.andrey7mel.testrx.presenter.vo.Repository;

import java.util.List;

public interface RepoListView extends View {

    void showRepoList(List<Repository> vo);

    void startRepoInfoFragment(Repository vo);

    void showEmptyList();

    String getUserName();
}
