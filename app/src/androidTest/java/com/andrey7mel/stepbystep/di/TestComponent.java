package com.andrey7mel.stepbystep.di;

import com.andrey7mel.stepbystep.RepoInfoTests;
import com.andrey7mel.stepbystep.RepoListTests;
import com.andrey7mel.stepbystep.other.di.AppComponent;
import com.andrey7mel.stepbystep.other.di.PresenterModule;
import com.andrey7mel.stepbystep.other.di.ViewModule;
import com.andrey7mel.stepbystep.tools.ApiConfig;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataTestModule.class,
        TestModelModule.class,
        PresenterModule.class,
        ViewModule.class})
public interface TestComponent extends AppComponent {

    void inject(ApiConfig apiConfig);

    void inject(RepoInfoTests repoInfoTests);

    void inject(RepoListTests repoListTests);

}
