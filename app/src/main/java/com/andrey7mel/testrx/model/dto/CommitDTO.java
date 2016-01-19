
package com.andrey7mel.testrx.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommitDTO {

    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * @return The sha
     */
    public String getSha() {
        return sha;
    }

    /**
     * @param sha The sha
     */
    public void setSha(String sha) {
        this.sha = sha;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
