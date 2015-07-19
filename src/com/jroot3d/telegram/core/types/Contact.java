package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.TelegramType;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class Contact extends TelegramType {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String userId;

    public Contact(JSONObject json) throws JSONException {
        super(json);

        phoneNumber = JsonUtil.getString(json, CFields.PHONE_NUMBER);
        firstName = JsonUtil.getString(json, CFields.FIRST_NAME);
        lastName = JsonUtil.getString(json, CFields.LAST_NAME);
        userId = JsonUtil.getString(json, CFields.USER_ID);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserId() {
        return userId;
    }
}
