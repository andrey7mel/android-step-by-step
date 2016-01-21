package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.model.ModelImpl;
import com.andrey7mel.testrx.presenter.BasePresenter;
import com.andrey7mel.testrx.presenter.RepoInfoPresenter;
import com.andrey7mel.testrx.presenter.RepoListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(RepoListPresenter repoListPresenter);

    void inject(RepoInfoPresenter repoInfoPresenter);

}
