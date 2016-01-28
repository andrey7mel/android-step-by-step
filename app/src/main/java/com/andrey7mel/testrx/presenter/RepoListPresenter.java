package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.model.Model;
import com.andrey7mel.testrx.model.ModelImpl;
import com.andrey7mel.testrx.model.data.Repo;
import com.andrey7mel.testrx.view.View;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class RepoListPresenter implements Presenter {

    private Model model = new ModelImpl();

    private View view;
    private Subscription subscription = Subscriptions.empty();

    public RepoListPresenter(View view) {
        this.view = view;
    }

    @Override
    public void onSearchButtonClick() {

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.getRepoList(view.getUserName())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Repo> data) {
                        if (data != null && !data.isEmpty()) {
                            view.showData(data);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
