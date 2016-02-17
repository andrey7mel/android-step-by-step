package com.andrey7mel.stepbystep.other.di;

import com.andrey7mel.stepbystep.presenter.RepoInfoPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class ViewTestModule {

    @Provides
    @Singleton
    RepoInfoPresenter provideRepoInfoPresenter() {
//        return spy(new RepoInfoPresenter());
        return mock(RepoInfoPresenter.class);
    }

}
