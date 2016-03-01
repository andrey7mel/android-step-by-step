package com.andrey7mel.stepbystep.tools;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andrey7mel.stepbystep.view.TestableProgressBar;
import com.google.common.truth.Truth;

import org.hamcrest.Matcher;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.view.View.FIND_VIEWS_WITH_TEXT;


public final class EspressoTools {

    public static ViewAssertion hasItemsCount(final int count) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof RecyclerView)) {
                    throw e;
                }
                RecyclerView rv = (RecyclerView) view;
                if (rv.getAdapter() == null && count == 0) {
                    return;
                }
                Truth.assertThat(rv.getAdapter().getItemCount()).isEqualTo(count);
            }
        };
    }

    public static ViewAssertion hasSetVisibilityCount(final int count) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof TestableProgressBar)) {
                    throw e;
                }
                TestableProgressBar testableProgressBar = (TestableProgressBar) view;
                Truth.assertThat(testableProgressBar.getCountSetVisibility()).isEqualTo(count);
            }
        };
    }

    public static ViewAssertion hasSetVISIBLECount(final int count) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof TestableProgressBar)) {
                    throw e;
                }
                TestableProgressBar testableProgressBar = (TestableProgressBar) view;
                Truth.assertThat(testableProgressBar.getCountSetVISIBLE()).isEqualTo(count);
            }
        };
    }

    public static ViewAssertion hasSetINVISIBLECount(final int count) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof TestableProgressBar)) {
                    throw e;
                }
                TestableProgressBar testableProgressBar = (TestableProgressBar) view;
                Truth.assertThat(testableProgressBar.getCountSetINVISIBLE()).isEqualTo(count);
            }
        };
    }

    public static ViewAssertion hasSetGONECount(final int count) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof TestableProgressBar)) {
                    throw e;
                }
                TestableProgressBar testableProgressBar = (TestableProgressBar) view;
                Truth.assertThat(testableProgressBar.getCountSetGONE()).isEqualTo(count);
            }
        };
    }



    public static ViewAssertion hasHolderItemAtPosition(final int index,
                                                        final Matcher<RecyclerView.ViewHolder> viewHolderMatcher) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof RecyclerView)) {
                    throw e;
                }
                RecyclerView rv = (RecyclerView) view;
                Assert.assertThat(rv.findViewHolderForAdapterPosition(index), viewHolderMatcher);
            }
        };
    }

    public static ViewAssertion hasViewWithTextAtPosition(final int index, final CharSequence text) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof RecyclerView)) {
                    throw e;
                }
                RecyclerView rv = (RecyclerView) view;
                ArrayList<View> outviews = new ArrayList<>();
                rv.findViewHolderForAdapterPosition(index).itemView.findViewsWithText(outviews, text,
                        FIND_VIEWS_WITH_TEXT);
                Truth.assert_().withFailureMessage("There's no view at index " + index + " of recyclerview that has text : " + text)
                        .that(outviews).isNotEmpty();
            }
        };
    }

    public static ViewAssertion doesntHaveViewWithText(final String text) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof RecyclerView)) {
                    throw e;
                }
                RecyclerView rv = (RecyclerView) view;
                ArrayList<View> outviews = new ArrayList<>();
                for (int index = 0; index < rv.getAdapter().getItemCount(); index++) {
                    rv.findViewHolderForAdapterPosition(index).itemView.findViewsWithText(outviews, text,
                            FIND_VIEWS_WITH_TEXT);
                    if (outviews.size() > 0) break;
                }
                Truth.assertThat(outviews).isEmpty();
            }
        };
    }

    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}
