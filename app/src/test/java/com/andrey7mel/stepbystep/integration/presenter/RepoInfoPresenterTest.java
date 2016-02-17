package com.andrey7mel.stepbystep.integration.presenter;

import android.os.Bundle;

import com.andrey7mel.stepbystep.integration.other.IntegrationBaseTest;
import com.andrey7mel.stepbystep.model.Model;
import com.andrey7mel.stepbystep.presenter.RepoInfoPresenter;
import com.andrey7mel.stepbystep.presenter.mappers.RepoBranchesMapper;
import com.andrey7mel.stepbystep.presenter.mappers.RepoContributorsMapper;
import com.andrey7mel.stepbystep.presenter.vo.Branch;
import com.andrey7mel.stepbystep.presenter.vo.Contributor;
import com.andrey7mel.stepbystep.presenter.vo.Repository;
import com.andrey7mel.stepbystep.view.fragments.RepoInfoView;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoInfoPresenterTest extends IntegrationBaseTest {


    @Inject
    protected List<Contributor> contributorList;

    @Inject
    protected List<Branch> branchList;

    @Inject
    protected RepoBranchesMapper branchesMapper;

    @Inject
    protected RepoContributorsMapper contributorsMapper;
    @Inject
    protected Repository repository;
    @Inject
    Model model;
    private RepoInfoView mockView;
    private RepoInfoPresenter repoInfoPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        mockView = mock(RepoInfoView.class);
        repoInfoPresenter = new RepoInfoPresenter();
        repoInfoPresenter.onCreate(mockView, repository);
    }


    @Test
    public void testLoadData() {
        repoInfoPresenter.onCreateView(null);
        repoInfoPresenter.onStop();


        verify(mockView).showBranches(branchList);
        verify(mockView).showContributors(contributorList);
    }


    @Test
    public void testLoadDataWithError() {
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                return new MockResponse().setResponseCode(500);
            }
        });

        repoInfoPresenter.onCreateView(null);
        repoInfoPresenter.onStop();

        verify(mockView, times(2)).showError("HTTP 500 OK");
    }


    @Test
    public void testSaveState() {
        repoInfoPresenter.onCreateView(null);

        Bundle bundle = Bundle.EMPTY;
        repoInfoPresenter.onSaveInstanceState(bundle);
        repoInfoPresenter.onStop();

        repoInfoPresenter.onCreateView(bundle);

        verify(mockView, times(2)).showBranches(branchList);
        verify(mockView, times(2)).showContributors(contributorList);
    }


}