package com.jroot3d.telegram.core.types.base;

import org.json.JSONObject;

/**
 * Created by oonufriychuk on 16.07.2015.
 */
public abstract class TelegramType {
    public JSONObject json;

    public TelegramType(JSONObject json) {
        this.json = json;
    }

    public JSONObject getJson() {
        return json;
    }

    @Override
    public String toString() {
        return json.toString();
    }
}
