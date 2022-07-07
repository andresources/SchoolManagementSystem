package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RazorPayKeysModel {
    @SerializedName("key_id")
    @Expose
    private String key_id;

    @SerializedName("key_secret")
    @Expose
    private String key_secret;

    @SerializedName("type")
    @Expose
    private String type;

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getKey_secret() {
        return key_secret;
    }

    public void setKey_secret(String key_secret) {
        this.key_secret = key_secret;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
