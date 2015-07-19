package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class Update {
    private JSONObject json;

    private int updateId;
    private Message message;

    public Update(JSONObject json) throws JSONException {
        this.json = json;

        updateId = JsonUtil.getInt(json, CFields.UPDATE_ID);
        message = JsonUtil.getMessage(json, CFields.MESSAGE);
    }

    public int getUpdateId() {
        return updateId;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return json.toString();
    }
}
