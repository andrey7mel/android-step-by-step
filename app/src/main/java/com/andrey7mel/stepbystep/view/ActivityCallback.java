package com.andrey7mel.stepbystep.view;

import com.andrey7mel.stepbystep.presenter.vo.Repository;

public interface ActivityCallback {

    void startRepoInfoFragment(Repository repository);

    void showProgressBar();

    void hideProgressBar();

}
