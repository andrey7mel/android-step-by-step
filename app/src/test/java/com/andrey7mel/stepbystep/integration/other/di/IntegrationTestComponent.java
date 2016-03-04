package com.andrey7mel.stepbystep.integration.other.di;

import com.andrey7mel.stepbystep.integration.model.ModelTest;
import com.andrey7mel.stepbystep.integration.other.IntegrationBaseTest;
import com.andrey7mel.stepbystep.integration.presenter.RepoInfoPresenterTest;
import com.andrey7mel.stepbystep.integration.presenter.RepoListPresenterTest;
import com.andrey7mel.stepbystep.integration.view.RepoInfoFragmentTest;
import com.andrey7mel.stepbystep.integration.view.RepoListFragmentTest;
import com.andrey7mel.stepbystep.other.di.AppComponent;
import com.andrey7mel.stepbystep.other.di.DataTestModule;
import com.andrey7mel.stepbystep.other.di.PresenterModule;
import com.andrey7mel.stepbystep.other.di.ViewModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {IntegrationTestModelModule.class, PresenterModule.class, ViewModule.class, DataTestModule.class})
public interface IntegrationTestComponent extends AppComponent {

    void inject(ModelTest modelTest);

    void inject(RepoInfoPresenterTest repoInfoPresenterTest);

    void inject(RepoListPresenterTest repoListPresenterTest);

    void inject(RepoInfoFragmentTest repoInfoFragmentTest);

    void inject(RepoListFragmentTest repoListFragmentTest);

    void inject(IntegrationBaseTest integrationBaseTest);

}
