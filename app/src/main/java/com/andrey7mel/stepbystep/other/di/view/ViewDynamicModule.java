package com.andrey7mel.stepbystep.other.di.view;

import com.andrey7mel.stepbystep.presenter.RepoListPresenter;
import com.andrey7mel.stepbystep.view.ActivityCallback;
import com.andrey7mel.stepbystep.view.fragments.RepoListView;

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
