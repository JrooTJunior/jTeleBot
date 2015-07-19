package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.TelegramType;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class Location extends TelegramType {
    private double longitude;
    private double latitude;

    public Location(JSONObject json) throws JSONException {
        super(json);

        longitude = JsonUtil.getFloat(json, CFields.LONGITUDE);
        latitude = JsonUtil.getFloat(json, CFields.LATITUDE);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
