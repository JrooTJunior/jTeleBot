package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.TelegramType;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class UserProfilePhotos extends TelegramType {
    private int totalCount;
    private ArrayList<PhotoSize> photos;

    public UserProfilePhotos(JSONObject json) throws JSONException {
        super(json);

        totalCount = JsonUtil.getInt(json, CFields.TOTAL_COUNT);
        photos = JsonUtil.getPhotos(json, CFields.PHOTOS);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public ArrayList<PhotoSize> getPhotos() {
        return photos;
    }
}
