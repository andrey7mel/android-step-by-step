package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.model.ModelImplTest;
import com.andrey7mel.testrx.presenter.RepoInfoPresenterTest;
import com.andrey7mel.testrx.presenter.RepoListPresenterTest;
import com.andrey7mel.testrx.presenter.mappers.RepoBranchesMapperTest;
import com.andrey7mel.testrx.presenter.mappers.RepoContributorsMapperTest;
import com.andrey7mel.testrx.presenter.mappers.RepoListMapperTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelTestModule.class, PresenterTestModule.class, ViewTestModule.class})
public interface TestComponent extends AppComponent {


    void inject(ModelImplTest dataRepositoryImplTest);

    void inject(RepoInfoPresenterTest repoInfoPresenterTest);

    void inject(RepoListPresenterTest repoListPresenterTest);

    void inject(RepoBranchesMapperTest repoBranchesMapperTest);

    void inject(RepoContributorsMapperTest repoContributorsMapperTest);

    void inject(RepoListMapperTest userReposMapperTest);
}
