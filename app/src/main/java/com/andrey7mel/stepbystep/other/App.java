package com.andrey7mel.stepbystep.other;

import android.app.Application;

import com.andrey7mel.stepbystep.other.di.AppComponent;
import com.andrey7mel.stepbystep.other.di.DaggerAppComponent;
import com.andrey7mel.stepbystep.other.di.ModelModule;
import com.andrey7mel.stepbystep.other.di.PresenterModule;

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
