package com.andrey7mel.testrx.presenter;

import android.os.Bundle;

import com.andrey7mel.testrx.other.TestConst;
import com.andrey7mel.testrx.view.fragments.RepoListView;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//TODO Fix tests without autoload
public class RepoListPresenterTest extends BaseForPresenterTest {

    private RepoListView mockView;
    private RepoListPresenter repoListPresenter;


    @Before
    public void setUp() throws Exception {
        super.setUp();

        mockView = mock(RepoListView.class);
        repoListPresenter = spy(new RepoListPresenter(mockView));

        doAnswer(invocation -> Observable.just(repositoryDTOs))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        doAnswer(invocation -> TestConst.TEST_OWNER)
                .when(mockView)
                .getUserName();
    }


    @Test
    public void testLoadData() {
        repoListPresenter.onCreate(null);
        repoListPresenter.onStop();

        verify(mockView, times(0)).showRepoList(repoList);
    }

    @Test
    public void testSubscribe() {
        repoListPresenter.onCreate(null);
        repoListPresenter.onSearchButtonClick();

        verify(repoListPresenter).addSubscription(any());

        repoListPresenter.onStop();
        assertTrue(repoListPresenter.compositeSubscription.isUnsubscribed());

    }

    @Test
    public void testSaveState() {
        repoListPresenter.onCreate(null);
        repoListPresenter.onSearchButtonClick();

        Bundle bundle = Bundle.EMPTY;
        repoListPresenter.onSaveInstanceState(bundle);
        repoListPresenter.onStop();

        repoListPresenter.onCreate(bundle);

        verify(mockView).showRepoList(repoList);

        verify(model).getRepoList(TestConst.TEST_OWNER);
    }
}