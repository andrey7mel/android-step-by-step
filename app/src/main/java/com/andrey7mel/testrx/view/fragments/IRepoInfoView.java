package com.andrey7mel.testrx.view.fragments;

import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;

import java.util.List;

public interface IRepoInfoView extends IView {

    void showContributors(List<ContributorVO> contributors);

    void showBranches(List<BranchVO> branches);

}
