package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.model.DataRepository;
import com.andrey7mel.testrx.model.IDataRepository;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements IPresenter {

    protected IDataRepository dataRepository = new DataRepository();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.unsubscribe();
    }

}
