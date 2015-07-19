package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.TelegramType;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class Audio extends TelegramType {
    private String fileId;
    private int duration;
    private String mimeType;
    private int fileSize;

    public Audio(JSONObject json) throws JSONException {
        super(json);

        fileId = JsonUtil.getString(json, CFields.FILE_ID);
        duration = JsonUtil.getInt(json, CFields.DURATION);
        mimeType = JsonUtil.getString(json, CFields.MIME_TYPE);
        fileSize = JsonUtil.getInt(json, CFields.FILE_SIZE);
    }

    public String getFileId() {
        return fileId;
    }

    public int getDuration() {
        return duration;
    }

    public String getMimeType() {
        return mimeType;
    }

    public int getFileSize() {
        return fileSize;
    }
}
