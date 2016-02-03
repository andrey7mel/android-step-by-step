package com.andrey7mel.testrx.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RepoListFragmentTest extends BaseTest {

    private RepoListFragment repoListFragment;

    private RepoListPresenter repoListPresenter;

    private MainActivity activity;

    private Bundle bundle;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        repoListFragment = new RepoListFragment();
        activity = Robolectric.setupActivity(MainActivity.class);
        bundle = Bundle.EMPTY;

        repoListPresenter = mock(RepoListPresenter.class);

        repoListFragment.onCreate(null); // need for DI
        repoListFragment.presenter = repoListPresenter;
    }


    @Test
    public void testOnCreateView() {
        repoListFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), null);
        verify(repoListPresenter).onCreateView(null);
    }

    @Test
    public void testOnCreateViewWithBundle() {
        repoListFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), bundle);
        verify(repoListPresenter).onCreateView(bundle);
    }

    @Test
    public void testOnStop() {
        repoListFragment.onStop();
        verify(repoListPresenter).onStop();
    }

    @Test
    public void testOnSaveInstanceState() {
        repoListFragment.onSaveInstanceState(null);
        verify(repoListPresenter).onSaveInstanceState(null);
    }

    @Test
    public void testOnSaveInstanceStateWithBundle() {
        repoListFragment.onSaveInstanceState(bundle);
        verify(repoListPresenter).onSaveInstanceState(bundle);
    }
}