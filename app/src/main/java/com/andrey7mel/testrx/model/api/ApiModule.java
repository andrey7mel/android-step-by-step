package com.andrey7mel.testrx.model.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class ApiModule {
    private static final boolean ENABLE_LOG = true;

    private static final boolean ENABLE_AUTH = false;
    private static final String AUTH_64 = "***";

    private static final String BASE_URL = "https://api.github.com/";

    public static ApiInterface getApiInterface() {

        OkHttpClient httpClient = new OkHttpClient();

        if (ENABLE_AUTH) {
            httpClient.interceptors().add(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Basic " + AUTH_64)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            });
        }

        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(interceptor);
        }

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.client(httpClient);

        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }

}
