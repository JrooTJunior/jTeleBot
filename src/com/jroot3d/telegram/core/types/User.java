package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.Chat;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class User extends Chat {

    private String firstName;
    private String lastName;
    private String username;

    public User(JSONObject json) throws JSONException {
        super(json);

        firstName = JsonUtil.getString(json, CFields.FIRST_NAME);
        lastName = JsonUtil.getString(json, CFields.LAST_NAME);
        username = JsonUtil.getString(json, CFields.USERNAME);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
