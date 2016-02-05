package com.andrey7mel.stepbystep.integration.other.di;

import com.andrey7mel.stepbystep.integration.other.IntegrationApiModule;
import com.andrey7mel.stepbystep.model.api.ApiInterface;
import com.andrey7mel.stepbystep.other.Const;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Module
public class IntegrationTestModelModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        try {
            return IntegrationApiModule.getApiInterface();
        } catch (IOException e) {
            throw new RuntimeException("Can't create ApiInterface");
        }
    }

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return Schedulers.immediate();
    }

    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.immediate();
    }
}
