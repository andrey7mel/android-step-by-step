package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.model.Model;
import com.andrey7mel.testrx.model.ModelImpl;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    protected Model dataRepository = new ModelImpl();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

}
