package com.andrey7mel.stepbystep.view.fragments;

public interface View {

    void showError(String error);

    void showLoadingState();

    void hideLoadingState();
}
