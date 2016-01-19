
package com.andrey7mel.testrx.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RequiredStatusChecksDTO {

    @SerializedName("enforcement_level")
    @Expose
    private String enforcementLevel;
    @SerializedName("contexts")
    @Expose
    private List<String> contexts = new ArrayList<String>();

    /**
     * @return The enforcementLevel
     */
    public String getEnforcementLevel() {
        return enforcementLevel;
    }

    /**
     * @param enforcementLevel The enforcement_level
     */
    public void setEnforcementLevel(String enforcementLevel) {
        this.enforcementLevel = enforcementLevel;
    }

    /**
     * @return The contexts
     */
    public List<String> getContexts() {
        return contexts;
    }

    /**
     * @param contexts The contexts
     */
    public void setContexts(List<String> contexts) {
        this.contexts = contexts;
    }

}
