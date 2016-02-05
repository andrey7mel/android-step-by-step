package com.andrey7mel.stepbystep.integration.other.di;

import com.andrey7mel.stepbystep.integration.model.ModelTest;
import com.andrey7mel.stepbystep.other.di.AppComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {IntegrationTestModelModule.class, IntegrationTestPresenterModule.class, IntegrationTestViewModule.class})
public interface IntegrationTestComponent extends AppComponent {
    void inject(ModelTest modelTest);
}
