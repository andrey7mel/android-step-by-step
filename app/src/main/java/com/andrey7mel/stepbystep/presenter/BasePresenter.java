package com.andrey7mel.stepbystep.presenter;

import com.andrey7mel.stepbystep.model.Model;
import com.andrey7mel.stepbystep.other.App;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    @Inject
    protected Model model;

    @Inject
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    void showLoadingState() {

    }

    void hideLoadingState() {
    }

}
