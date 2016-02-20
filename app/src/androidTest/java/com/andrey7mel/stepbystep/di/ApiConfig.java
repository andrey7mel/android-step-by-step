package com.andrey7mel.stepbystep.di;

import com.andrey7mel.stepbystep.model.api.ApiInterface;
import com.andrey7mel.stepbystep.model.dto.BranchDTO;
import com.andrey7mel.stepbystep.model.dto.ContributorDTO;
import com.andrey7mel.stepbystep.model.dto.RepositoryDTO;
import com.andrey7mel.stepbystep.tools.TestConst;
import com.andrey7mel.stepbystep.tools.TestUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.Mockito.when;

public class ApiConfig {

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    protected List<RepositoryDTO> repositoryDTOs;

    @Inject
    protected List<ContributorDTO> contributorDTOs;

    @Inject
    protected List<BranchDTO> branchDTOs;

    TestUtils testUtils = new TestUtils();

    @Inject
    public ApiConfig() {
//        setCorrectAnswer();
    }

    public void setCorrectAnswer() {

        when(apiInterface.getRepositories(TestConst.TEST_OWNER))
                .thenReturn(Observable.just((repositoryDTOs)));

        when(apiInterface.getBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(Observable.just((branchDTOs)));

        when(apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(Observable.just((contributorDTOs)));

    }

    public void setErrorAnswer() {
        when(apiInterface.getRepositories(TestConst.TEST_OWNER))
                .thenReturn(Observable.error(new Throwable(TestConst.TEST_ERROR)));

        when(apiInterface.getBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(Observable.error(new Throwable(TestConst.TEST_ERROR)));

        when(apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(Observable.error(new Throwable(TestConst.TEST_ERROR)));
    }

    public void setCustomAnswer(boolean enableBranches, boolean enableContributors) {

        when(apiInterface.getRepositories(TestConst.TEST_OWNER))
                .thenReturn(Observable.just((repositoryDTOs)));

        when(apiInterface.getBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(enableBranches ? Observable.just((branchDTOs))
                        : Observable.error(new Throwable(TestConst.TEST_ERROR)));

        when(apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(enableContributors ? Observable.just((contributorDTOs))
                        : Observable.error(new Throwable(TestConst.TEST_ERROR)));
    }

}
