package com.andrey7mel.stepbystep.tools;

import com.andrey7mel.stepbystep.model.api.ApiInterface;
import com.andrey7mel.stepbystep.model.dto.BranchDTO;
import com.andrey7mel.stepbystep.model.dto.ContributorDTO;
import com.andrey7mel.stepbystep.model.dto.RepositoryDTO;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Inject
    public ApiConfig() {
    }

    public void setCorrectAnswer() {

        when(apiInterface.getRepositories(TestConst.TEST_OWNER))
                .thenReturn(getObservableWithDelay((repositoryDTOs)));

        when(apiInterface.getBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(getObservableWithDelay((branchDTOs)));

        when(apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(getObservableWithDelay((contributorDTOs)));

    }

    public void setErrorAnswer() {
        when(apiInterface.getRepositories(TestConst.TEST_OWNER))
                .thenReturn(getErrorObservableWithDelay());

        when(apiInterface.getBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(getErrorObservableWithDelay());

        when(apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(getErrorObservableWithDelay());
    }

    public void setCustomAnswer(boolean enableBranches, boolean enableContributors) {

        when(apiInterface.getRepositories(TestConst.TEST_OWNER))
                .thenReturn(Observable.just((repositoryDTOs)));

        when(apiInterface.getBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(enableBranches ? getObservableWithDelay((branchDTOs))
                        : getErrorObservableWithDelay());

        when(apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO))
                .thenReturn(enableContributors ? getObservableWithDelay((contributorDTOs))
                        : getErrorObservableWithDelay());
    }

    private <T> Observable<T> getObservableWithDelay(final T value) {
        return Observable.timer(TestConst.API_DELAY, TimeUnit.MILLISECONDS)
                .concatMap(aLong -> Observable.just(value));
    }

    private <T> Observable<T> getErrorObservableWithDelay() {
        return Observable.timer(TestConst.API_DELAY, TimeUnit.MILLISECONDS)
                .concatMap(aLong -> Observable.error(new Throwable(TestConst.TEST_ERROR)));
    }

}
