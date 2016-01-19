package com.andrey7mel.testrx.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.andrey7mel.testrx.presenter.mappers.UserReposMapper;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;
import com.andrey7mel.testrx.view.fragments.IRepoListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class RepoListPresenter extends BasePresenter {

    private static final String BUNDLE_REPO_LIST_KEY = "BUNDLE_REPO_LIST_KEY";


    private IRepoListView view;

    private UserReposMapper userReposMapper = new UserReposMapper();

    private List<RepositoryVO> repoList;

    public RepoListPresenter(IRepoListView view) {
        this.view = view;
    }

    public void loadData() {
        String name = view.getInputName();
        if (TextUtils.isEmpty(name)) return;

        Subscription subscription = dataRepository.getRepoList(name)
                .map(userReposMapper)
                .subscribe(new Observer<List<RepositoryVO>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<RepositoryVO> list) {
                        if (list != null && !list.isEmpty()) {
                            repoList = list;
                            view.setRepoList(list);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription);
    }

    public void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            repoList = (List<RepositoryVO>) savedInstanceState.getSerializable(BUNDLE_REPO_LIST_KEY);
        }

        if (repoList == null) {
            loadData();
        } else {
            view.setRepoList(repoList);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (repoList != null)
            outState.putSerializable(BUNDLE_REPO_LIST_KEY, new ArrayList<>(repoList));
    }

    public void clickRepo(RepositoryVO repositoryVO) {
        view.startRepoInfoFragment(repositoryVO);
    }

}
