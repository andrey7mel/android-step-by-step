package com.andrey7mel.stepbystep;

import com.andrey7mel.stepbystep.model.api.ApiInterface;
import com.andrey7mel.stepbystep.model.api.ApiModule;
import com.mosn.asyncmockwebserver.AsyncMockWebServer;
import com.mosn.asyncmockwebserver.Mock;
import com.mosn.asyncmockwebserver.MockDispatcher;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import java.io.IOException;

public final class TestApiModule {

    public static MockWebServer server = new MockWebServer();

    private TestApiModule() {
    }

    public static ApiInterface getApiInterface() throws IOException {
//        MockWebServer server = new MockWebServer();
//
//        new Thread(() -> {
//            try {
//                server.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        TestUtils testUtils = new TestUtils();
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
        return ApiModule.getApiInterface(baseUrl.toString());
    }


    public static ApiInterface getApiInterface2() throws IOException {

        TestUtils testUtils = new TestUtils();

        AsyncMockWebServer.addMock(Mock.create(
                "/users/" + TestConst.TEST_OWNER + "/repos",
                new MockResponse().setResponseCode(200).setBody(testUtils.readString("json/repos")),
                new MockDispatcher() {
                    @Override
                    public boolean isDispatch(RecordedRequest request) {
                        return request.getPath().contains("/users/" + TestConst.TEST_OWNER + "/repos");
                    }
                }
        ));

        AsyncMockWebServer.addMock(Mock.create(
                "/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/branches",
                new MockResponse().setResponseCode(200).setBody(testUtils.readString("json/branches")),
                new MockDispatcher() {
                    @Override
                    public boolean isDispatch(RecordedRequest request) {
                        return request.getPath().contains("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/branches");
                    }
                }
        ));

        AsyncMockWebServer.addMock(Mock.create(
                "/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/contributors",
                new MockResponse().setResponseCode(200).setBody(testUtils.readString("json/contributors")),
                new MockDispatcher() {
                    @Override
                    public boolean isDispatch(RecordedRequest request) {
                        return request.getPath().contains("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/contributors");
                    }
                }
        ));
//        final Dispatcher dispatcher = new Dispatcher() {
//
//            @Override
//            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
//
//                if (request.getPath().equals("/users/" + TestConst.TEST_OWNER + "/repos")) {
//                    return new MockResponse().setResponseCode(200)
//                            .setBody(testUtils.readString("json/repos"));
//                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/branches")) {
//                    return new MockResponse().setResponseCode(200)
//                            .setBody(testUtils.readString("json/branches"));
//                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/contributors")) {
//                    return new MockResponse().setResponseCode(200)
//                            .setBody(testUtils.readString("json/contributors"));
//                }
//                return new MockResponse().setResponseCode(404);
//            }
//        };


        return ApiModule.getApiInterface(AsyncMockWebServer.getEndPoint());
    }
}
