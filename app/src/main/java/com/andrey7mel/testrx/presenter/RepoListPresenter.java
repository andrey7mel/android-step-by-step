package com.andrey7mel.testrx.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.andrey7mel.testrx.presenter.mappers.UserReposMapper;
import com.andrey7mel.testrx.presenter.vo.Repository;
import com.andrey7mel.testrx.view.fragments.RepoListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class RepoListPresenter extends BasePresenterImpl {

    private static final String BUNDLE_REPO_LIST_KEY = "BUNDLE_REPO_LIST_KEY";

    private RepoListView view;

    private UserReposMapper userReposMapper = new UserReposMapper();

    private List<Repository> repoList;

    public RepoListPresenter(RepoListView view) {
        this.view = view;
    }

    public void onSearchButtonClick() {
        String name = view.getUserName();
        if (TextUtils.isEmpty(name)) return;

        Subscription subscription = dataRepository.getRepoList(name)
                .map(userReposMapper)
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Repository> list) {
                        if (list != null && !list.isEmpty()) {
                            repoList = list;
                            view.showRepoList(list);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subscription);
    }

    public void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            repoList = (List<Repository>) savedInstanceState.getSerializable(BUNDLE_REPO_LIST_KEY);
        }

        if (repoList == null) {
            onSearchButtonClick();
        } else {
            view.showRepoList(repoList);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (repoList != null)
            outState.putSerializable(BUNDLE_REPO_LIST_KEY, new ArrayList<>(repoList));
    }

    public void clickRepo(Repository repository) {
        view.startRepoInfoFragment(repository);
    }

}
