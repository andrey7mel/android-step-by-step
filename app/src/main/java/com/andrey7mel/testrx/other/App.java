package com.andrey7mel.testrx.other;

import android.app.Application;

import com.andrey7mel.testrx.other.di.AppComponent;
import com.andrey7mel.testrx.other.di.DaggerAppComponent;
import com.andrey7mel.testrx.other.di.ModelModule;
import com.andrey7mel.testrx.other.di.PresenterModule;

public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .modelModule(new ModelModule())
                .presenterModule(new PresenterModule())
                .build();
    }


}
