package com.andrey7mel.stepbystep.other.di.view;

import com.andrey7mel.stepbystep.presenter.RepoListPresenter;
import com.andrey7mel.stepbystep.view.fragments.RepoListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private RepoListView view;

    public ViewDynamicModule(RepoListView view) {
        this.view = view;
    }

    @Provides
    RepoListPresenter provideRepoListPresenter() {
        return new RepoListPresenter(view);
    }

}
