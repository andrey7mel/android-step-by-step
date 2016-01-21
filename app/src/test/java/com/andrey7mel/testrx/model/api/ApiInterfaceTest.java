package com.andrey7mel.testrx.model.api;

import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.other.TestConst;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.observers.TestSubscriber;

public class ApiInterfaceTest extends BaseTest {


    MockWebServer server;
    ApiInterface apiInterface;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("/users/" + TestConst.TEST_OWNER + "/repos")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/repos"));
                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/branches")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/branches"));
                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/contributors")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/contributors"));
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url("/");

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        apiInterface = builder.build().create(ApiInterface.class);
    }


    @Test
    public void testGetRepositories() throws Exception {

        TestSubscriber<List<RepositoryDTO>> testSubscriber = new TestSubscriber<>();
        apiInterface.getRepositories(TestConst.TEST_OWNER).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<RepositoryDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(7, actual.size());
        assertEquals("Android-Rate", actual.get(0).getName());
        assertEquals("andrey7mel/Android-Rate", actual.get(0).getFullName());
        assertEquals(26314692, actual.get(0).getId());
    }

    @Test
    public void testGetRepositoriesIncorrect() throws Exception {
        try {
            apiInterface.getRepositories("IncorrectRequest").subscribe();
            fail();
        } catch (Exception expected) {
            assertEquals("HTTP 404 OK", expected.getMessage());
        }
    }


    @Test
    public void testGetContributors() {
        TestSubscriber<List<ContributorDTO>> testSubscriber = new TestSubscriber<>();
        apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<ContributorDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(11, actual.size());
        assertEquals("hotchemi", actual.get(0).getLogin());
        assertEquals("User", actual.get(0).getType());
        assertEquals(471318, actual.get(0).getId());

    }

    @Test
    public void testGetContributorsIncorrect() throws Exception {
        try {
            apiInterface.getContributors("BBB", "AAA").subscribe();
            fail();
        } catch (Exception expected) {
            assertEquals("HTTP 404 OK", expected.getMessage());
        }
    }


    @Test
    public void testGetBranches() {
        TestSubscriber<List<BranchDTO>> testSubscriber = new TestSubscriber<>();
        apiInterface.getBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<BranchDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(3, actual.size());
        assertEquals("QuickStart", actual.get(0).getName());
        assertEquals("94870e23f1cfafe7201bf82985b61188f650b245", actual.get(0).getCommit().getSha());

    }

    @Test
    public void testGetBranchesIncorrect() throws Exception {
        try {
            apiInterface.getContributors("A", "B").subscribe();
            fail();
        } catch (Exception expected) {
            assertEquals("HTTP 404 OK", expected.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}