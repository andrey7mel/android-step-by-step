package com.andrey7mel.stepbystep.integration.other;

import com.andrey7mel.stepbystep.integration.other.di.DaggerIntegrationTestComponent;
import com.andrey7mel.stepbystep.integration.other.di.IntegrationTestModelModule;
import com.andrey7mel.stepbystep.integration.other.di.IntegrationTestPresenterModule;
import com.andrey7mel.stepbystep.other.App;
import com.andrey7mel.stepbystep.other.di.AppComponent;

public class IntegrationTestApp extends App {

    @Override
    protected AppComponent buildComponent() {
        return DaggerIntegrationTestComponent.builder()
                .integrationTestModelModule(new IntegrationTestModelModule())
                .integrationTestPresenterModule(new IntegrationTestPresenterModule())
                .build();
    }
}
