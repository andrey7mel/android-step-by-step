package com.andrey7mel.stepbystep.di;

import com.andrey7mel.stepbystep.other.di.AppComponent;
import com.andrey7mel.stepbystep.other.di.PresenterModule;
import com.andrey7mel.stepbystep.other.di.ViewModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestModelModule.class, PresenterModule.class, ViewModule.class})
public interface TestComponent extends AppComponent {

}
