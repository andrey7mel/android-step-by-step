package com.andrey7mel.stepbystep.di;

import com.andrey7mel.stepbystep.model.dto.BranchDTO;
import com.andrey7mel.stepbystep.model.dto.ContributorDTO;
import com.andrey7mel.stepbystep.model.dto.RepositoryDTO;
import com.andrey7mel.stepbystep.presenter.mappers.RepoBranchesMapper;
import com.andrey7mel.stepbystep.presenter.mappers.RepoContributorsMapper;
import com.andrey7mel.stepbystep.presenter.mappers.RepoListMapper;
import com.andrey7mel.stepbystep.presenter.vo.Branch;
import com.andrey7mel.stepbystep.presenter.vo.Contributor;
import com.andrey7mel.stepbystep.presenter.vo.Repository;
import com.andrey7mel.stepbystep.tools.TestUtils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataTestModule {

    private TestUtils testUtils;

    public DataTestModule() {
        testUtils = new TestUtils();
    }

    @Provides
    @Singleton
    TestUtils provideTestUtils() {
        return testUtils;
    }


    @Provides
    @Singleton
    List<ContributorDTO> provideContributorDTOList() {
        ContributorDTO[] contributorDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/contributors"), ContributorDTO[].class);
        return Arrays.asList(contributorDTOArray);
    }


    @Provides
    @Singleton
    List<BranchDTO> provideBranchDTOList() {
        BranchDTO[] branchDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/branches"), BranchDTO[].class);
        return Arrays.asList(branchDTOArray);
    }

    @Provides
    @Singleton
    List<RepositoryDTO> provideRepositoryDTOList() {
        RepositoryDTO[] repositoryDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/repos"), RepositoryDTO[].class);
        return Arrays.asList(repositoryDTOArray);
    }

    @Provides
    @Singleton
    List<Repository> provideRepositoryList(RepoListMapper repoListMapper, List<RepositoryDTO> list) {
        return repoListMapper.call(list);
    }

    @Provides
    @Singleton
    List<Contributor> provideContributorList(RepoContributorsMapper contributorsMapper, List<ContributorDTO> contributorDTOs) {
        return contributorsMapper.call(contributorDTOs);
    }

    @Provides
    @Singleton
    List<Branch> provideBranchList(RepoBranchesMapper branchesMapper, List<BranchDTO> branchDTOs) {
        return branchesMapper.call(branchDTOs);
    }

}