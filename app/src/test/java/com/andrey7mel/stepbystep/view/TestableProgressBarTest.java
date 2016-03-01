package com.andrey7mel.stepbystep.view;

import android.view.View;

import junit.framework.TestCase;

import org.robolectric.RuntimeEnvironment;

public class TestableProgressBarTest extends TestCase {

    private final int NUMBER_SET_VISIBILITY = 10;
    TestableProgressBar testableProgressBar;

    public void setUp() throws Exception {
        super.setUp();
        testableProgressBar = new TestableProgressBar(RuntimeEnvironment.application);
    }

    public void testSetVisible() throws Exception {
        testableProgressBar.setVisibility(View.VISIBLE);

        assertEquals(1, testableProgressBar.getCountSetVISIBLE());
        assertEquals(1, testableProgressBar.getCountSetVisibility());
    }

    public void testSetVisibleMany() throws Exception {
        for (int i = 0; i < NUMBER_SET_VISIBILITY; i++) {
            testableProgressBar.setVisibility(View.VISIBLE);
        }

        assertEquals(NUMBER_SET_VISIBILITY, testableProgressBar.getCountSetVISIBLE());
        assertEquals(NUMBER_SET_VISIBILITY, testableProgressBar.getCountSetVisibility());
    }

    public void testSetInvisible() throws Exception {

        testableProgressBar.setVisibility(View.INVISIBLE);

        assertEquals(1, testableProgressBar.getCountSetINVISIBLE());
        assertEquals(1, testableProgressBar.getCountSetVisibility());
    }

    public void testSetInvisibleMany() throws Exception {

        for (int i = 0; i < NUMBER_SET_VISIBILITY; i++) {
            testableProgressBar.setVisibility(View.INVISIBLE);
        }

        assertEquals(NUMBER_SET_VISIBILITY, testableProgressBar.getCountSetINVISIBLE());
        assertEquals(NUMBER_SET_VISIBILITY, testableProgressBar.getCountSetVisibility());
    }

    public void testSetGone() throws Exception {

        testableProgressBar.setVisibility(View.GONE);

        assertEquals(1, testableProgressBar.getCountSetGONE());
        assertEquals(1, testableProgressBar.getCountSetVisibility());
    }

    public void testSetGoneMany() throws Exception {


        for (int i = 0; i < NUMBER_SET_VISIBILITY; i++) {
            testableProgressBar.setVisibility(View.GONE);
        }

        assertEquals(NUMBER_SET_VISIBILITY, testableProgressBar.getCountSetGONE());
        assertEquals(NUMBER_SET_VISIBILITY, testableProgressBar.getCountSetVisibility());
    }

    public void setVisibility() throws Exception {
        testableProgressBar.setVisibility(View.VISIBLE);
        testableProgressBar.setVisibility(View.INVISIBLE);
        testableProgressBar.setVisibility(View.GONE);

        assertEquals(1, testableProgressBar.getCountSetVISIBLE());
        assertEquals(1, testableProgressBar.getCountSetINVISIBLE());
        assertEquals(1, testableProgressBar.getCountSetGONE());
        assertEquals(3, testableProgressBar.getCountSetVisibility());
    }

    public void setVisibilityMany() throws Exception {

        for (int i = 0; i < NUMBER_SET_VISIBILITY; i++) {
            testableProgressBar.setVisibility(View.VISIBLE);
            testableProgressBar.setVisibility(View.INVISIBLE);
            testableProgressBar.setVisibility(View.GONE);
        }

        assertEquals(NUMBER_SET_VISIBILITY, testableProgressBar.getCountSetVISIBLE());
        assertEquals(3 * NUMBER_SET_VISIBILITY, testableProgressBar.getCountSetVisibility());
    }
}