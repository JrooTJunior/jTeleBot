package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.ReplyMarkup;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class ReplyKeyboardHide extends ReplyMarkup {
    private boolean hideKeyboard;

    public ReplyKeyboardHide(JSONObject json) throws JSONException {
        super(json);

        hideKeyboard = JsonUtil.getBoolean(json, CFields.HIDE_KEYBOARD);
    }

    public boolean isHideKeyboard() {
        return hideKeyboard;
    }
}
