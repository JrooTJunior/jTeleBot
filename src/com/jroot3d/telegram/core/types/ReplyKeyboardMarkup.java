package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.ReplyMarkup;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class ReplyKeyboardMarkup extends ReplyMarkup {
    private String[][] keyboard;
    private boolean resizeKeyboard;
    private boolean oneTimeKeyboard;

    public ReplyKeyboardMarkup(JSONObject json) throws JSONException {
        super(json);

        keyboard = JsonUtil.getStringArrays(json, CFields.KEYBOARD);
        resizeKeyboard = JsonUtil.getBoolean(json, CFields.RESIZE_KEYBOARD);
        oneTimeKeyboard = JsonUtil.getBoolean(json, CFields.ONE_TIME_KEYBOARD);
    }

    public String[][] getKeyboard() {
        return keyboard;
    }

    public boolean isResizeKeyboard() {
        return resizeKeyboard;
    }

    public boolean isOneTimeKeyboard() {
        return oneTimeKeyboard;
    }
}
