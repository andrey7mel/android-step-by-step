package com.andrey7mel.testrx.view;

import com.andrey7mel.testrx.model.data.Repo;

import java.util.List;

public interface View {

    void showData(List<Repo> list);

    void showError(Throwable e);

    String getUserName();
}
