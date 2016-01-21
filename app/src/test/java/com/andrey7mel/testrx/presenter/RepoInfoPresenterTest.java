package com.andrey7mel.testrx.presenter;

import android.os.Bundle;

import com.andrey7mel.testrx.other.TestConst;
import com.andrey7mel.testrx.presenter.vo.Repository;
import com.andrey7mel.testrx.view.fragments.RepoInfoView;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoInfoPresenterTest extends BaseForPresenterTest {

    private RepoInfoView mockView;
    private RepoInfoPresenter repoInfoPresenter;
    private Repository repository;


    @Before
    public void setUp() throws Exception {
        super.setUp();

        repository = new Repository(TestConst.TEST_REPO, TestConst.TEST_OWNER);
        mockView = mock(RepoInfoView.class);
        repoInfoPresenter = spy(new RepoInfoPresenter(mockView, repository));

        doAnswer(invocation -> Observable.just(branchDTOs))
                .when(model)
                .getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);

        doAnswer(invocation -> Observable.just(contributorDTOs))
                .when(model)
                .getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);
    }


    @Test
    public void testLoadData() {
        repoInfoPresenter.onCreate(null);
        repoInfoPresenter.onStop();

        verify(mockView).showBranches(branchList);
        verify(mockView).showContributors(contributorList);
    }

    @Test
    public void testSubscribe() {
        repoInfoPresenter.onCreate(null);

        verify(repoInfoPresenter, times(2)).addSubscription(any());

        repoInfoPresenter.onStop();
        assertTrue(repoInfoPresenter.compositeSubscription.isUnsubscribed());

    }

    @Test
    public void testSaveState() {
        repoInfoPresenter.onCreate(null);

        Bundle bundle = Bundle.EMPTY;
        repoInfoPresenter.onSaveInstanceState(bundle);
        repoInfoPresenter.onStop();

//        repoInfoPresenter = new RepoInfoPresenter(mockView, repository);
        repoInfoPresenter.onCreate(bundle);

        verify(mockView, times(2)).showBranches(branchList);
        verify(mockView, times(2)).showContributors(contributorList);

        verify(model).getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);
        verify(model).getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);
    }
}