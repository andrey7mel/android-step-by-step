package com.andrey7mel.stepbystep.presenter;

import com.andrey7mel.stepbystep.view.fragments.View;

public interface Presenter {
    void onCreate(View view);
    void onStop();

}
