package com.andrey7mel.stepbystep.presenter;

import com.andrey7mel.stepbystep.model.Model;
import com.andrey7mel.stepbystep.other.App;
import com.andrey7mel.stepbystep.view.fragments.View;

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

    protected abstract View getView();

    protected void showLoadingState() {
        getView().showLoadingState();
    }

    protected void hideLoadingState() {
        getView().hideLoadingState();
    }

    protected void showError(Throwable e) {
        getView().showError(e.getMessage());
    }

}
