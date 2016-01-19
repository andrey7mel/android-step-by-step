package com.andrey7mel.testrx.presenter.vo;

import java.io.Serializable;

public class Repository implements Serializable {
    private String repoName;
    private String ownerName;

    public Repository(String repoName, String ownerName) {
        this.repoName = repoName;
        this.ownerName = ownerName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

}
