
package com.andrey7mel.testrx.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProtectionDTO {

    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("required_status_checks")
    @Expose
    private RequiredStatusChecksDTO requiredStatusChecks;

    /**
     * @return The enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled The enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return The requiredStatusChecks
     */
    public RequiredStatusChecksDTO getRequiredStatusChecks() {
        return requiredStatusChecks;
    }

    /**
     * @param requiredStatusChecks The required_status_checks
     */
    public void setRequiredStatusChecks(RequiredStatusChecksDTO requiredStatusChecks) {
        this.requiredStatusChecks = requiredStatusChecks;
    }

}
