package com.andrey7mel.stepbystep.view.fragments;

import com.andrey7mel.stepbystep.presenter.vo.Branch;
import com.andrey7mel.stepbystep.presenter.vo.Contributor;

import java.util.List;

public interface RepoInfoView extends View {

    void showContributors(List<Contributor> contributors);

    void showBranches(List<Branch> branches);

}
