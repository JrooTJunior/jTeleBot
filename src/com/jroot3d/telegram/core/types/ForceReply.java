package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.ReplyMarkup;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class ForceReply extends ReplyMarkup {
    private boolean forceReply;

    public ForceReply(JSONObject json) throws JSONException {
        super(json);

        forceReply = JsonUtil.getBoolean(json, CFields.FORCE_REPLY);
    }

    public boolean isForceReply() {
        return forceReply;
    }
}
