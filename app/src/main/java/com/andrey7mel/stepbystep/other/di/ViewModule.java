package com.andrey7mel.stepbystep.other.di;

import com.andrey7mel.stepbystep.presenter.RepoInfoPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    RepoInfoPresenter provideRepoInfoPresenter() {
        return new RepoInfoPresenter();
    }

}
