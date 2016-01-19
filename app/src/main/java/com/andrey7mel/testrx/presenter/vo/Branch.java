package com.andrey7mel.testrx.presenter.vo;

import java.io.Serializable;

public class Branch implements Serializable {
    private String name;

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
