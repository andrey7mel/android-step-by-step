package com.andrey7mel.testrx.view.fragments;

import android.support.v4.app.Fragment;

import com.andrey7mel.testrx.presenter.BasePresenter;

public abstract class BaseFragment extends Fragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

}

