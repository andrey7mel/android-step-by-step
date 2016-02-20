package com.andrey7mel.stepbystep.di;

import com.andrey7mel.stepbystep.other.App;

public class TestApp extends App {

    @Override
    protected TestComponent buildComponent() {
        return DaggerTestComponent.builder().build();
    }
}
