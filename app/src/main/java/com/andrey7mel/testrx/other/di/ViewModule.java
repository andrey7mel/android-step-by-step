package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.presenter.RepoInfoPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    RepoInfoPresenter provideRepoInfoPresenter() {
        return new RepoInfoPresenter();
    }

}
