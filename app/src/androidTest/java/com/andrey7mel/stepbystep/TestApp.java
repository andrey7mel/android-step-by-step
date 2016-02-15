package com.andrey7mel.stepbystep;

import com.andrey7mel.stepbystep.di.DaggerTestComponent;
import com.andrey7mel.stepbystep.other.App;
import com.andrey7mel.stepbystep.other.di.AppComponent;

public class TestApp extends App {

    @Override
    protected AppComponent buildComponent() {
        return DaggerTestComponent.builder().build();
    }
}
