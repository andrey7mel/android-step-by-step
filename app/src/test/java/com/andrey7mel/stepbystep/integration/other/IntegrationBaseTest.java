package com.andrey7mel.stepbystep.integration.other;

import com.andrey7mel.stepbystep.BuildConfig;
import com.andrey7mel.stepbystep.integration.other.di.IntegrationTestComponent;
import com.andrey7mel.stepbystep.other.App;
import com.andrey7mel.stepbystep.other.TestUtils;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

@RunWith(RobolectricGradleTestRunner.class)
@Config(application = IntegrationTestApp.class,
        constants = BuildConfig.class,
        sdk = 21)
@Ignore
public class IntegrationBaseTest extends Assert {

    public IntegrationTestComponent component;
    public TestUtils testUtils;

    @Inject
    protected MockWebServer mockWebServer;

    @Before
    public void setUp() throws Exception {
        component = (IntegrationTestComponent) App.getComponent();
        testUtils = new TestUtils();
        component.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }
}
