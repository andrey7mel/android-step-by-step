package com.andrey7mel.testrx.presenter.vo;

import java.io.Serializable;

public class BranchVO implements Serializable {
    private String name;

    public BranchVO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
