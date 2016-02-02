package com.andrey7mel.testrx.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.andrey7mel.testrx.R;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.other.TestConst;
import com.andrey7mel.testrx.presenter.RepoInfoPresenter;
import com.andrey7mel.testrx.presenter.vo.Repository;
import com.andrey7mel.testrx.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;

public class RepoInfoFragmentTest extends BaseTest {

    RepoInfoFragment repoInfoFragment;

    @Inject
    RepoInfoPresenter repoInfoPresenter;

    Repository repository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        repository = new Repository(TestConst.TEST_REPO, TestConst.TEST_OWNER);

        repoInfoFragment = RepoInfoFragment.newInstance(repository);
    }


    @Test
    public void testOnCreate() {
        repoInfoFragment.onCreate(null);
        verify(repoInfoPresenter).onCreate(repoInfoFragment, repository);
    }

    @Test
    public void testOnCreateView() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        repoInfoFragment.onCreate(null); // need for DI
        repoInfoFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), null);
        verify(repoInfoPresenter).onCreateView(null);
    }

    @Test
    public void testOnCreateViewWithBundle() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        Bundle bundle = Bundle.EMPTY;
        repoInfoFragment.onCreate(null); // need for DI
        repoInfoFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), bundle);
        verify(repoInfoPresenter).onCreateView(bundle);
    }

    @Test
    public void testOnStop() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        repoInfoFragment.onCreate(null); // need for DI
        repoInfoFragment.onStop();
        verify(repoInfoPresenter).onStop();
    }

    @Test
    public void testOnSaveInstanceState() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        repoInfoFragment.onCreate(null); // need for DI
        repoInfoFragment.onSaveInstanceState(null);
        verify(repoInfoPresenter).onSaveInstanceState(null);
    }

    @Test
    public void testOnSaveInstanceStateWithBundle() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        Bundle bundle = Bundle.EMPTY;
        repoInfoFragment.onCreate(null); // need for DI
        repoInfoFragment.onSaveInstanceState(bundle);
        verify(repoInfoPresenter).onSaveInstanceState(bundle);
    }
}