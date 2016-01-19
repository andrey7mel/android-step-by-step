package com.andrey7mel.testrx.presenter.vo;

import java.io.Serializable;

public class ContributorVO implements Serializable {
    private String name;

    public ContributorVO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
