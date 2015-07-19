package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.TelegramType;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class Video extends TelegramType {
    private String fileId;
    private int width;
    private int height;
    private int duration;
    private PhotoSize thumb;
    private String mimeType;
    private int fileSize;
    private String caption;

    public Video(JSONObject json) throws JSONException {
        super(json);

        fileId = JsonUtil.getString(json, CFields.FILE_ID);
        width = JsonUtil.getInt(json, CFields.WIDTH);
        height = JsonUtil.getInt(json, CFields.HEIGHT);
        duration = JsonUtil.getInt(json, CFields.DURATION);
        thumb = JsonUtil.getPhoto(json, CFields.THUMB);
        mimeType = JsonUtil.getString(json, CFields.MIME_TYPE);
        fileSize = JsonUtil.getInt(json, CFields.FILE_SIZE);
        caption = JsonUtil.getString(json, CFields.CAPTION);

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

    public int getDuration() {
        return duration;
    }

    public PhotoSize getThumb() {
        return thumb;
    }

    public String getMimeType() {
        return mimeType;
    }

    public int getFileSize() {
        return fileSize;
    }

    public String getCaption() {
        return caption;
    }
}
