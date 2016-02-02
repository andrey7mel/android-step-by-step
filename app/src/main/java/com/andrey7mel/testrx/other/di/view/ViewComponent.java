package com.andrey7mel.testrx.other.di.view;

import com.andrey7mel.testrx.view.fragments.RepoListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(RepoListFragment repoListFragment);

}
