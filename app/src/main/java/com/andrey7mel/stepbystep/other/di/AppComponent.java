package com.andrey7mel.stepbystep.other.di;

import com.andrey7mel.stepbystep.model.ModelImpl;
import com.andrey7mel.stepbystep.presenter.BasePresenter;
import com.andrey7mel.stepbystep.presenter.RepoInfoPresenter;
import com.andrey7mel.stepbystep.presenter.RepoListPresenter;
import com.andrey7mel.stepbystep.view.fragments.RepoInfoFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(RepoListPresenter repoListPresenter);

    void inject(RepoInfoPresenter repoInfoPresenter);

    void inject(RepoInfoFragment repoInfoFragment);
}
