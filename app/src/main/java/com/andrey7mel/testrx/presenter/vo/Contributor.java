package com.andrey7mel.testrx.presenter.vo;

import java.io.Serializable;

public class Contributor implements Serializable {
    private String name;

    public Contributor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
