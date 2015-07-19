package com.jroot3d.telegram.core.types.base;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 16.07.2015.
 */
public abstract class ReplyMarkup extends TelegramType {
    private boolean selective;

    public ReplyMarkup(JSONObject json) throws JSONException {
        super(json);

        selective = JsonUtil.getBoolean(json, CFields.SELECTIVE);
    }

    public boolean isSelective() {
        return selective;
    }
}
