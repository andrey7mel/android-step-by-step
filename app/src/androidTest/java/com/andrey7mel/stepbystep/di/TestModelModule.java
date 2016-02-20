package com.andrey7mel.stepbystep.di;

import com.andrey7mel.stepbystep.model.api.ApiInterface;
import com.andrey7mel.stepbystep.other.Const;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

@Module
public class TestModelModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return mock(ApiInterface.class);
    }

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

}
