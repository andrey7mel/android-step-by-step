package com.andrey7mel.stepbystep.integration.other.di;

import com.andrey7mel.stepbystep.presenter.RepoInfoPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class IntegrationTestViewModule {

    @Provides
    @Singleton
    RepoInfoPresenter provideRepoInfoPresenter() {
        return new RepoInfoPresenter();
    }

}
