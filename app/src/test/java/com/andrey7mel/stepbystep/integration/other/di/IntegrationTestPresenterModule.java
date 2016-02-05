package com.andrey7mel.stepbystep.integration.other.di;

import com.andrey7mel.stepbystep.model.Model;
import com.andrey7mel.stepbystep.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.spy;

@Module

public class IntegrationTestPresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return spy(new ModelImpl());
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
