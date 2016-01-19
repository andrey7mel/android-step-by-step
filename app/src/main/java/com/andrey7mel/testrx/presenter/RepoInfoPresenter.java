package com.andrey7mel.testrx.presenter;

import android.os.Bundle;

import com.andrey7mel.testrx.presenter.mappers.RepoBranchesMapper;
import com.andrey7mel.testrx.presenter.mappers.RepoContributorsMapper;
import com.andrey7mel.testrx.presenter.vo.BranchVO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;
import com.andrey7mel.testrx.view.fragments.IRepoInfoView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class RepoInfoPresenter extends BasePresenter {

    public static final String BUNDLE_BRANCHES_KEY = "BUNDLE_BRANCHES_KEY";
    public static final String BUNDLE_CONTRIBUTORS_KEY = "BUNDLE_CONTRIBUTORS_KEY";
    private IRepoInfoView view;
    private RepoBranchesMapper branchesMapper = new RepoBranchesMapper();
    private RepoContributorsMapper contributorsMapper = new RepoContributorsMapper();
    private List<ContributorVO> contributorList;
    private List<BranchVO> branchList;
    private RepositoryVO repositoryVO;

    public RepoInfoPresenter(IRepoInfoView view, RepositoryVO repositoryVO) {
        this.view = view;
        this.repositoryVO = repositoryVO;
    }

    public void loadData() {
        String owner = repositoryVO.getOwnerName();
        String name = repositoryVO.getRepoName();

        Subscription subscriptionBranches = dataRepository.getRepoBranches(owner, name)
                .map(branchesMapper)
                .subscribe(new Observer<List<BranchVO>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<BranchVO> list) {
                        branchList = list;
                        view.showBranches(list);
                    }
                });
        addSubscription(subscriptionBranches);

        Subscription subscriptionContributors = dataRepository.getRepoContributors(owner, name)
                .map(contributorsMapper)
                .subscribe(new Observer<List<ContributorVO>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<ContributorVO> list) {
                        contributorList = list;
                        view.showContributors(list);
                    }
                });

        addSubscription(subscriptionContributors);
    }

    public void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            contributorList = (List<ContributorVO>) savedInstanceState.getSerializable(BUNDLE_CONTRIBUTORS_KEY);
            branchList = (List<BranchVO>) savedInstanceState.getSerializable(BUNDLE_BRANCHES_KEY);
        }

        if (contributorList == null || branchList == null) {
            loadData();
        } else {
            view.showBranches(branchList);
            view.showContributors(contributorList);
        }

    }

    public void onSaveInstanceState(Bundle outState) {
        if (contributorList != null)
            outState.putSerializable(BUNDLE_CONTRIBUTORS_KEY, new ArrayList<>(contributorList));
        if (branchList != null)
            outState.putSerializable(BUNDLE_BRANCHES_KEY, new ArrayList<>(branchList));

    }
}
