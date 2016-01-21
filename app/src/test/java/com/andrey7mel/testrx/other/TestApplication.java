package com.andrey7mel.testrx.other;

import com.andrey7mel.testrx.other.di.AppComponent;
import com.andrey7mel.testrx.other.di.DaggerTestComponent;
import com.andrey7mel.testrx.other.di.ModelTestModule;
import com.andrey7mel.testrx.other.di.PresenterTestModule;

public class TestApplication extends App {

    @Override
    protected AppComponent buildComponent() {
        return DaggerTestComponent.builder()
                .modelTestModule(new ModelTestModule())
                .presenterTestModule(new PresenterTestModule())
                .build();
    }
}
