package com.andrey7mel.stepbystep.presenter;

import com.andrey7mel.stepbystep.other.BaseTest;
import com.andrey7mel.stepbystep.other.TestConst;
import com.andrey7mel.stepbystep.view.fragments.RepoListView;
import com.andrey7mel.stepbystep.view.fragments.View;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import rx.Subscription;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BasePresenterTest extends BaseTest {

    protected View view;
    private BasePresenter basePresenter;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        RepoListView repoListView = mock(RepoListView.class);
        basePresenter = new RepoListPresenter(repoListView);
        view = repoListView;
    }

    @Test
    public void testShowError() throws Exception {
        Throwable throwable = new Throwable(TestConst.TEST_ERROR);
        basePresenter.showError(throwable);
        verify(view).showError(TestConst.TEST_ERROR);
    }

    @Test
    public void testAddSubscription() throws Exception {
        Subscription test = new TestSubscriber<>();
        basePresenter.addSubscription(test);
        assertTrue(basePresenter.compositeSubscription.hasSubscriptions());
    }

    @Test
    public void testOnStop() throws Exception {
        Subscription test = new TestSubscriber<>();
        basePresenter.addSubscription(test);
        basePresenter.onStop();
        assertTrue(test.isUnsubscribed());
    }

    @Test
    public void testOnStopManySubscription() throws Exception {
        final int num_subscription = 100;
        ArrayList<Subscription> subscriptionList = new ArrayList<>();

        for (int i = 0; i < num_subscription; i++) {
            Subscription test = new TestSubscriber<>();
            basePresenter.addSubscription(test);
            subscriptionList.add(test);
        }

        basePresenter.onStop();

        for (Subscription subscription : subscriptionList) {
            assertTrue(subscription.isUnsubscribed());
        }
    }
}