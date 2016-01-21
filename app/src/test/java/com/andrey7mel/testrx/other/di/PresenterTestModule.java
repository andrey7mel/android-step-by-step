package com.andrey7mel.testrx.other.di;

import com.andrey7mel.testrx.model.Model;
import com.andrey7mel.testrx.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.spy;

@Module

public class PresenterTestModule {

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
