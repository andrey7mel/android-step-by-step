package com.andrey7mel.stepbystep.view.fragments;

import com.andrey7mel.stepbystep.other.BaseTest;
import com.andrey7mel.stepbystep.presenter.BasePresenter;
import com.andrey7mel.stepbystep.presenter.vo.Repository;
import com.andrey7mel.stepbystep.view.MainActivity;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BaseFragmentTest extends BaseTest {

    BaseFragment baseFragment;
    MainActivity activity;

    BasePresenter basePresenter;

    @Inject
    Repository repository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        activity = mock(MainActivity.class);

        RepoInfoFragment repoInfoFragment = RepoInfoFragment.newInstance(repository);
        baseFragment = repoInfoFragment;
        baseFragment.onCreate(null); //for Di
        baseFragment.onAttach(activity); //for link activity

        basePresenter = repoInfoFragment.presenter;
    }

    @Test
    public void testShowLoadingState() throws Exception {
        baseFragment.showLoadingState();
        verify(activity).showLoadingState();

    }

    @Test
    public void testHideLoadingState() throws Exception {
        baseFragment.hideLoadingState();
        verify(activity).hideLoadingState();
    }

    @Test
    public void testOnStop() {
        baseFragment.onStop();
        verify(basePresenter).onStop();
    }
}