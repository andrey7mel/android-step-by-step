package com.andrey7mel.stepbystep.view.fragments;

import com.andrey7mel.stepbystep.presenter.vo.Repository;

import java.util.List;

public interface RepoListView extends View {

    void showRepoList(List<Repository> vo);

    void showEmptyList();

    String getUserName();
}
