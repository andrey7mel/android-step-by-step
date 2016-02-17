package com.andrey7mel.stepbystep.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.andrey7mel.stepbystep.R;
import com.andrey7mel.stepbystep.other.BaseTest;
import com.andrey7mel.stepbystep.other.TestConst;
import com.andrey7mel.stepbystep.presenter.RepoInfoPresenter;
import com.andrey7mel.stepbystep.presenter.vo.Repository;
import com.andrey7mel.stepbystep.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import javax.inject.Inject;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoInfoFragmentTest extends BaseTest {

    @Inject
    RepoInfoPresenter repoInfoPresenter;
    private RepoInfoFragment repoInfoFragment;
    private Repository repository;

    private MainActivity activity;

    private Bundle bundle;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        repository = new Repository(TestConst.TEST_REPO, TestConst.TEST_OWNER);

        repoInfoFragment = RepoInfoFragment.newInstance(repository);
        activity = Robolectric.setupActivity(MainActivity.class);
        bundle = Bundle.EMPTY;

        repoInfoFragment.onCreate(null); // need for DI
    }


    @Test
    public void testOnCreate() {
        repoInfoFragment.onCreate(null);
        verify(repoInfoPresenter, times(2)).onCreate(repoInfoFragment, repository); //2 times setUp() + testOnCreate()
    }

    @Test
    public void testOnCreateView() {
        repoInfoFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), null);
        verify(repoInfoPresenter).onCreateView(null);
    }

    @Test
    public void testOnCreateViewWithBundle() {
        repoInfoFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), bundle);
        verify(repoInfoPresenter).onCreateView(bundle);
    }

    @Test
    public void testOnStop() {
        repoInfoFragment.onStop();
        verify(repoInfoPresenter).onStop();
    }

    @Test
    public void testOnSaveInstanceState() {
        repoInfoFragment.onSaveInstanceState(null);
        verify(repoInfoPresenter).onSaveInstanceState(null);
    }

    @Test
    public void testOnSaveInstanceStateWithBundle() {
        repoInfoFragment.onSaveInstanceState(bundle);
        verify(repoInfoPresenter).onSaveInstanceState(bundle);
    }
}