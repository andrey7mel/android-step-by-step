package com.andrey7mel.stepbystep;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrey7mel.stepbystep.di.TestApiModule;
import com.andrey7mel.stepbystep.tools.EspressoTools;
import com.andrey7mel.stepbystep.view.MainActivity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {

    //Todo негативные сценарии, нормальная работа с вебсервером
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @BeforeClass
    public static void setUpServer() throws Exception {
        TestApiModule.startServer();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        TestApiModule.shutdownServer();
    }

    @Test
    public void testElementsDisplayed() {
        onView(withId(R.id.button_search)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_text)).check(matches(isDisplayed()));
    }

    @Test
    public void testGetUserRepo() {
        TestApiModule.setCorrectAnswer();
        onView(withId(R.id.edit_text)).perform(clearText());
        onView(withId(R.id.edit_text)).perform(typeText(TestConst.TEST_OWNER));
        onView(withId(R.id.button_search)).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.recycler_view)).check(EspressoTools.hasItemsCount(7));

        onView(withId(R.id.recycler_view)).check(EspressoTools.hasViewWithTextAtPosition(0, "Android-Rate"));
        onView(withId(R.id.recycler_view)).check(EspressoTools.hasViewWithTextAtPosition(1, "android-simple-architecture"));
        onView(withId(R.id.recycler_view)).check(EspressoTools.hasViewWithTextAtPosition(2, TestConst.TEST_REPO));
    }

    @Test
    public void testClickUserRepo() {
        TestApiModule.setCorrectAnswer();
        onView(withId(R.id.edit_text)).perform(clearText());
        onView(withId(R.id.edit_text)).perform(typeText(TestConst.TEST_OWNER));
        onView(withId(R.id.button_search)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, click()));
        SystemClock.sleep(1000);

        //check view items
        onView(withId(R.id.repo_info))
                .check(matches(isDisplayed()))
                .check(matches(withText(TestConst.TEST_REPO + " (" + TestConst.TEST_OWNER + ")")));
        onView(withId(R.id.branches_title))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.branches)));
        onView(withId(R.id.contributors_title))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.contributors)));
        onView(withId(R.id.recycler_view_branches)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_contributors)).check(matches(isDisplayed()));

        //check RecyclerView count
        onView(withId(R.id.recycler_view_branches)).check(EspressoTools.hasItemsCount(3));
        onView(withId(R.id.recycler_view_contributors)).check(EspressoTools.hasItemsCount(11));

        //check RecyclerView items
        onView(withId(R.id.recycler_view_branches)).check(EspressoTools.hasViewWithTextAtPosition(0, "QuickStart"));
        onView(withId(R.id.recycler_view_branches)).check(EspressoTools.hasViewWithTextAtPosition(1, "gh-pages"));
        onView(withId(R.id.recycler_view_branches)).check(EspressoTools.hasViewWithTextAtPosition(2, "master"));

        onView(withId(R.id.recycler_view_contributors)).check(EspressoTools.hasViewWithTextAtPosition(0, "hotchemi"));
        onView(withId(R.id.recycler_view_contributors)).check(EspressoTools.hasViewWithTextAtPosition(1, "mrmike"));
        onView(withId(R.id.recycler_view_contributors)).check(EspressoTools.hasViewWithTextAtPosition(2, "amitkot"));
    }

    @Test
    public void testGetUserRepoError() {
        TestApiModule.setErrorAnswer();
        onView(withId(R.id.edit_text)).perform(clearText());
        onView(withId(R.id.edit_text)).perform(typeText(TestConst.TEST_OWNER));
        onView(withId(R.id.button_search)).perform(click());
        SystemClock.sleep(1000);

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(TestConst.TEST_ERROR)))
                .check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view)).check(EspressoTools.hasItemsCount(0));
    }

    @Test
    public void testClickUserRepoError() {
        TestApiModule.setCorrectAnswer();
        onView(withId(R.id.edit_text)).perform(clearText());
        onView(withId(R.id.edit_text)).perform(typeText(TestConst.TEST_OWNER));
        onView(withId(R.id.button_search)).perform(click());
        SystemClock.sleep(1000);

        TestApiModule.setErrorAnswer();
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, click()));
        SystemClock.sleep(1000);

        //check view items
        onView(withId(R.id.repo_info))
                .check(matches(isDisplayed()))
                .check(matches(withText(TestConst.TEST_REPO + " (" + TestConst.TEST_OWNER + ")")));
        onView(withId(R.id.branches_title))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.branches)));
        onView(withId(R.id.contributors_title))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.contributors)));
        onView(withId(R.id.recycler_view_branches)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_contributors)).check(matches(isDisplayed()));

        //check RecyclerView count
        onView(withId(R.id.recycler_view_branches)).check(EspressoTools.hasItemsCount(0));
        onView(withId(R.id.recycler_view_contributors)).check(EspressoTools.hasItemsCount(0));


        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(TestConst.TEST_ERROR)))
                .check(matches(isDisplayed()));
    }

}