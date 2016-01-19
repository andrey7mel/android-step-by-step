package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.model.IDataRepository;
import com.andrey7mel.testrx.model.data.Repo;
import com.andrey7mel.testrx.presenter.filters.RepoListFilter;
import com.andrey7mel.testrx.view.IView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class RepoListPresenter implements IPresenter {

    private IDataRepository dataRepository = new DataRepository();

    private RepoListFilter filter;
    private IView view;
    private Subscription subscription = Subscriptions.empty();


    public RepoListPresenter(IView view) {
        this.view = view;
    }


    @Override
    public void loadData() {
        subscription = dataRepository.getRepoList(filter)
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e);
                    }

                    @Override
                    public void onNext(List<Repo> data) {
                        view.inflateData(data);
                    }
                });
    }

    @Override
    public void setFilter(RepoListFilter filter) {
        this.filter = filter;
    }

    @Override
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
