package com.andrey7mel.testrx.other.di.view;

import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.view.fragments.RepoListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    RepoListView view;

    public ViewDynamicModule(RepoListView view) {
        this.view = view;
    }

    @Provides
    RepoListPresenter provideRepoListPresenter() {
        return new RepoListPresenter(view);
    }


}
