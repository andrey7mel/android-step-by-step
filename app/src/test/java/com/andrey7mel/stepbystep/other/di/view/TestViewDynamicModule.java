package com.andrey7mel.stepbystep.other.di.view;

import com.andrey7mel.stepbystep.presenter.RepoListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestViewDynamicModule {

    @Provides
    @Singleton
    RepoListPresenter provideRepoListPresenter() {
        return mock(RepoListPresenter.class);
    }

}
