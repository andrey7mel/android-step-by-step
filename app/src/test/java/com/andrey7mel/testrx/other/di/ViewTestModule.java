package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.presenter.RepoInfoPresenter;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;

@Module
public class ViewTestModule {

    @Provides
    RepoInfoPresenter provideRepoInfoPresenter() {
        return spy(new RepoInfoPresenter());
    }

}
