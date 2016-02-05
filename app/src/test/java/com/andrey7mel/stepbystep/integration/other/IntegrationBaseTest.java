package com.andrey7mel.stepbystep.integration.other;

import com.andrey7mel.stepbystep.BuildConfig;
import com.andrey7mel.stepbystep.integration.other.di.IntegrationTestComponent;
import com.andrey7mel.stepbystep.other.App;
import com.andrey7mel.stepbystep.other.TestUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(application = IntegrationTestApp.class,
        constants = BuildConfig.class,
        sdk = 21)
@Ignore
public class IntegrationBaseTest extends Assert {

    public IntegrationTestComponent component;
    public TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        component = (IntegrationTestComponent) App.getComponent();
        testUtils = new TestUtils();
    }
}
