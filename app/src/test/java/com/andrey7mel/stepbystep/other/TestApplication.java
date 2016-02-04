package com.andrey7mel.stepbystep.other;

import com.andrey7mel.stepbystep.other.di.AppComponent;
import com.andrey7mel.stepbystep.other.di.DaggerTestComponent;
import com.andrey7mel.stepbystep.other.di.ModelTestModule;
import com.andrey7mel.stepbystep.other.di.PresenterTestModule;

public class TestApplication extends App {

    @Override
    protected AppComponent buildComponent() {
        return DaggerTestComponent.builder()
                .modelTestModule(new ModelTestModule())
                .presenterTestModule(new PresenterTestModule())
                .build();
    }
}
