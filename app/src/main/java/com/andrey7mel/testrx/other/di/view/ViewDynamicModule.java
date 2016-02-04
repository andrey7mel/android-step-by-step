package com.andrey7mel.testrx.other.di.view;

import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.view.ActivityCallback;
import com.andrey7mel.testrx.view.fragments.RepoListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private RepoListView view;

    private ActivityCallback activityCallback;

    public ViewDynamicModule(RepoListView view, ActivityCallback activityCallback) {
        this.view = view;
        this.activityCallback = activityCallback;
    }

    @Provides
    RepoListPresenter provideRepoListPresenter() {
        return new RepoListPresenter(view, activityCallback);
    }


}
