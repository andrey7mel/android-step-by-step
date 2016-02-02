package com.andrey7mel.testrx.presenter;

import android.os.Bundle;

import com.andrey7mel.testrx.model.Model;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.other.TestConst;
import com.andrey7mel.testrx.presenter.mappers.RepoListMapper;
import com.andrey7mel.testrx.presenter.vo.Repository;
import com.andrey7mel.testrx.view.fragments.RepoListView;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//TODO Fix tests without autoload
public class RepoListPresenterTest extends BaseTest {

    @Inject
    protected RepoListMapper repoListMapper;
    protected List<Repository> repoList;
    @Inject
    Model model;
    private RepoListView mockView;
    private RepoListPresenter repoListPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        RepositoryDTO[] repositoryDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/repos"), RepositoryDTO[].class);
        List<RepositoryDTO> repositoryDTOs = Arrays.asList(repositoryDTOArray);
        repoList = repoListMapper.call(repositoryDTOs);


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
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();
        repoListPresenter.onStop();

        verify(mockView).showRepoList(repoList);
    }

    @Test
    public void testSubscribe() {
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();
        repoListPresenter.onStop();


        // Нужно перехватить Subscription в методе addSubscription и проверить что она отписана
        verify(repoListPresenter).addSubscription(any());
        assertTrue(repoListPresenter.compositeSubscription.isUnsubscribed());

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