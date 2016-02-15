package com.andrey7mel.stepbystep;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrey7mel.stepbystep.view.MainActivity;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @BeforeClass
    public static void setUpServer() throws Exception {
        TestApiModule.server.start();
    }

//    @Test
//    public void testSearchButtonDisplayed() {
//        onView(withId(R.id.button_search)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void testEditTextDisplayed() {
//        onView(withId(R.id.edit_text)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void testGetUserRepo(){
//        onView(withId(R.id.edit_text)).perform(clearText());
//        onView(withId(R.id.edit_text)).perform(typeText(TestConst.TEST_OWNER));
//        onView(withId(R.id.button_search)).perform(click());
//        SystemClock.sleep(1000);
//        onView(withId(R.id.recycler_view)).check(EspressoTools.hasViewWithTextAtPosition(0, "Android-Rate"));
//        onView(withId(R.id.recycler_view)).check(EspressoTools.hasViewWithTextAtPosition(1, "android-simple-architecture"));
//        onView(withId(R.id.recycler_view)).check(EspressoTools.hasViewWithTextAtPosition(2, TestConst.TEST_REPO));
//
//    }

    @Test
    public void testClickUserRepo() {
        onView(withId(R.id.edit_text)).perform(clearText());
        onView(withId(R.id.edit_text)).perform(typeText(TestConst.TEST_OWNER));
        onView(withId(R.id.button_search)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, click()));
        SystemClock.sleep(1000);

    }


}