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

    private View view;

    @Override
    public void onCreate(View view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    protected void showLoadingState() {
        view.showLoadingState();
    }


    protected void hideLoadingState() {
        view.hideLoadingState();
    }
}
