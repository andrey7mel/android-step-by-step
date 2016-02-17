package com.andrey7mel.stepbystep.other.di.view;

import com.andrey7mel.stepbystep.view.fragments.RepoListFragmentTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestViewDynamicModule.class})
public interface TestViewComponent extends ViewComponent {

    void inject(RepoListFragmentTest in);

}
