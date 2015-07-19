package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.Chat;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class GroupChat extends Chat {
    private String title;

    public GroupChat(JSONObject json) throws JSONException {
        super(json);

        title = JsonUtil.getString(json, CFields.TITLE);
    }

    public String getTitle() {
        return title;
    }
}
