package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.TelegramType;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class PhotoSize extends TelegramType {

    private String fileId;
    private int width;
    private int height;
    private int fileSize;

    public PhotoSize(JSONObject json) throws JSONException {
        super(json);

        fileId = JsonUtil.getString(json, CFields.FILE_ID);
        width = JsonUtil.getInt(json, CFields.WIDTH);
        height = JsonUtil.getInt(json, CFields.HEIGHT);
        fileSize = JsonUtil.getInt(json, CFields.FILE_SIZE);
    }

    public String getFileId() {
        return fileId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFileSize() {
        return fileSize;
    }
}
