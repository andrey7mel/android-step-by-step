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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Repository that = (Repository) o;

        if (repoName != null ? !repoName.equals(that.repoName) : that.repoName != null)
            return false;
        return !(ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null);

    }

    @Override
    public int hashCode() {
        int result = repoName != null ? repoName.hashCode() : 0;
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        return result;
    }
}
