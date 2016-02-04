package com.andrey7mel.testrx.presenter;

import android.os.Bundle;

import com.andrey7mel.testrx.model.Model;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.other.TestConst;
import com.andrey7mel.testrx.presenter.mappers.RepoListMapper;
import com.andrey7mel.testrx.presenter.vo.Repository;
import com.andrey7mel.testrx.view.ActivityCallback;
import com.andrey7mel.testrx.view.fragments.RepoListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoListPresenterTest extends BaseTest {

    @Inject
    RepoListMapper repoListMapper;
    @Inject
    Model model;
    ActivityCallback activityCallback;
    private List<Repository> repoList;
    private RepoListView mockView;
    private RepoListPresenter repoListPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        RepositoryDTO[] repositoryDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/repos"), RepositoryDTO[].class);
        List<RepositoryDTO> repositoryDTOs = Arrays.asList(repositoryDTOArray);
        repoList = repoListMapper.call(repositoryDTOs);
        activityCallback = mock(ActivityCallback.class);

        mockView = mock(RepoListView.class);
        repoListPresenter = spy(new RepoListPresenter(mockView, activityCallback));

        doAnswer(invocation -> Observable.just(repositoryDTOs))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        doAnswer(invocation -> TestConst.TEST_OWNER)
                .when(mockView)
                .getUserName();
    }


    @Test
    public void testLoadData() {
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();
        repoListPresenter.onStop();

        verify(mockView).showRepoList(repoList);
    }

    @Test
    public void testLoadNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        repoListPresenter.onSearchButtonClick();

        verify(mockView).showEmptyList();
    }

    @Test
    public void testLoadEmptyData() {
        doAnswer(invocation -> Observable.just(Collections.emptyList()))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        repoListPresenter.onSearchButtonClick();

        verify(mockView).showEmptyList();
    }


    @Test
    public void testOnError() {
        doAnswer(invocation -> Observable.error(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        repoListPresenter.onSearchButtonClick();

        verify(mockView).showError(TestConst.TEST_ERROR);
    }

    @Test
    public void testEmptyName() {
        doAnswer(invocation -> "")
                .when(mockView)
                .getUserName();

        repoListPresenter.onSearchButtonClick();

        verify(mockView, never()).showEmptyList();
        verify(mockView, never()).showRepoList(repoList);

    }

    @Test
    public void testClickRepo() {
        Repository repository = new Repository(TestConst.TEST_REPO, TestConst.TEST_OWNER);

        repoListPresenter.clickRepo(repository);

        verify(activityCallback).startRepoInfoFragment(repository);
    }

    @Test
    public void testSubscribe() {
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();
        repoListPresenter.onStop();

        ArgumentCaptor<Subscription> captor = ArgumentCaptor.forClass(Subscription.class);
        verify(repoListPresenter).addSubscription(captor.capture());
        List<Subscription> subscriptions = captor.getAllValues();
        assertEquals(1, subscriptions.size());
        assertTrue(subscriptions.get(0).isUnsubscribed());
    }

    @Test
    public void testSaveState() {
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();

        Bundle bundle = Bundle.EMPTY;
        repoListPresenter.onSaveInstanceState(bundle);
        repoListPresenter.onStop();

        repoListPresenter.onCreateView(bundle);

        verify(mockView, times(2)).showRepoList(repoList);
        verify(model).getRepoList(TestConst.TEST_OWNER);
    }
}