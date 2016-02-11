package com.andrey7mel.stepbystep.integration.other.di;

import com.andrey7mel.stepbystep.model.Model;
import com.andrey7mel.stepbystep.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module

public class IntegrationTestPresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
