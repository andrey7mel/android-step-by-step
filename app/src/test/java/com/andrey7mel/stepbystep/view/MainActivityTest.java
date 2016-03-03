package com.andrey7mel.stepbystep.view;

import android.view.View;
import android.widget.ProgressBar;

import com.andrey7mel.stepbystep.other.BaseTest;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainActivityTest extends BaseTest {

    private MainActivity mainActivity;
    private ProgressBar progressBar;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mainActivity = new MainActivity();
        progressBar = mock(ProgressBar.class);
        mainActivity.progressBar = progressBar;
    }

    @Test
    public void testShowProgressBar() throws Exception {
        mainActivity.showProgressBar();
        verify(progressBar).setVisibility(View.VISIBLE);
    }

    @Test
    public void testHideProgressBar() throws Exception {
        mainActivity.hideProgressBar();
        verify(progressBar).setVisibility(View.INVISIBLE);
    }
}