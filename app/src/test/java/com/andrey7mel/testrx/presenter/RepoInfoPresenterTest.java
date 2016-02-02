package com.andrey7mel.testrx.presenter;

import android.os.Bundle;

import com.andrey7mel.testrx.model.Model;
import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.other.TestConst;
import com.andrey7mel.testrx.presenter.mappers.RepoBranchesMapper;
import com.andrey7mel.testrx.presenter.mappers.RepoContributorsMapper;
import com.andrey7mel.testrx.presenter.vo.Branch;
import com.andrey7mel.testrx.presenter.vo.Contributor;
import com.andrey7mel.testrx.presenter.vo.Repository;
import com.andrey7mel.testrx.view.fragments.RepoInfoView;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoInfoPresenterTest extends BaseTest {

    protected List<ContributorDTO> contributorDTOs;
    protected List<BranchDTO> branchDTOs;
    protected List<Contributor> contributorList;
    protected List<Branch> branchList;
    @Inject
    protected RepoBranchesMapper branchesMapper;
    @Inject
    protected RepoContributorsMapper contributorsMapper;
    @Inject
    Model model;
    private RepoInfoView mockView;
    private RepoInfoPresenter repoInfoPresenter;
    private Repository repository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        contributorList = contributorsMapper.call(contributorDTOs);
        branchList = branchesMapper.call(branchDTOs);

        repository = new Repository(TestConst.TEST_REPO, TestConst.TEST_OWNER);
        mockView = mock(RepoInfoView.class);
        repoInfoPresenter = spy(new RepoInfoPresenter());
        repoInfoPresenter.onCreate(mockView, repository);

        doAnswer(invocation -> Observable.just(branchDTOs))
                .when(model)
                .getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);

        doAnswer(invocation -> Observable.just(contributorDTOs))
                .when(model)
                .getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);


    }


    @Test
    public void testLoadData() {
        repoInfoPresenter.onCreateView(null);
        repoInfoPresenter.onStop();


        verify(mockView).showBranches(branchList);
        verify(mockView).showContributors(contributorList);
    }

    @Test
    public void testSubscribe() {
        repoInfoPresenter.onCreateView(null);

        verify(repoInfoPresenter, times(2)).addSubscription(any());

        repoInfoPresenter.onStop();
        assertTrue(repoInfoPresenter.compositeSubscription.isUnsubscribed());

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

        verify(model).getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);
        verify(model).getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);
    }
}