package com.andrey7mel.testrx.view.fragments;

import com.andrey7mel.testrx.presenter.vo.RepositoryVO;

import java.util.List;

public interface IRepoListView extends IView {

    void setRepoList(List<RepositoryVO> vo);

    void startRepoInfoFragment(RepositoryVO vo);

    void showEmptyList();

    String getInputName();
}
