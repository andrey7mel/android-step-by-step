package com.andrey7mel.stepbystep.other.di;

import com.andrey7mel.stepbystep.model.Model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.mock;

@Module

public class PresenterTestModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return mock(Model.class);
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
