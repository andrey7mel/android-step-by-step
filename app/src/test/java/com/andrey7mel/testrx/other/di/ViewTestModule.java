package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.presenter.RepoInfoPresenter;

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
