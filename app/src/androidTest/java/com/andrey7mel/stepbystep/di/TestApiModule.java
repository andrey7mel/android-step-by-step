package com.andrey7mel.stepbystep.di;

import com.andrey7mel.stepbystep.TestConst;
import com.andrey7mel.stepbystep.model.api.ApiInterface;
import com.andrey7mel.stepbystep.model.api.ApiModule;
import com.andrey7mel.stepbystep.tools.TestUtils;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import java.io.IOException;

public final class TestApiModule {

    private static MockWebServer server;
    private static TestUtils testUtils = new TestUtils();

    private TestApiModule() {
    }

    public static void startServer() throws IOException {
        server = new MockWebServer();
        server.start();
    }

    public static void shutdownServer() throws IOException {
        if (server != null) {
            server.shutdown();
        }
    }

    public static void setCorrectAnswer() {
        Dispatcher dispatcher = new Dispatcher() {

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
    }

    public static void setErrorAnswer() {
        Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                return new MockResponse().setResponseCode(500);
            }
        };
        server.setDispatcher(dispatcher);
    }

    public static ApiInterface getApiInterface() throws IOException {
        setCorrectAnswer();
        HttpUrl baseUrl = server.url("/");
        return ApiModule.getApiInterface(baseUrl.toString());
    }

}
