
package com.andrey7mel.testrx.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Permissions {

    @SerializedName("admin")
    @Expose
    private boolean admin;
    @SerializedName("push")
    @Expose
    private boolean push;
    @SerializedName("pull")
    @Expose
    private boolean pull;

    /**
     * @return The admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin The admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @return The push
     */
    public boolean isPush() {
        return push;
    }

    /**
     * @param push The push
     */
    public void setPush(boolean push) {
        this.push = push;
    }

    /**
     * @return The pull
     */
    public boolean isPull() {
        return pull;
    }

    /**
     * @param pull The pull
     */
    public void setPull(boolean pull) {
        this.pull = pull;
    }

}
