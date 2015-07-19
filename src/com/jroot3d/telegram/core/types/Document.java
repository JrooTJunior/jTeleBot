package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.TelegramType;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class Document extends TelegramType {
    private String fileId;
    private PhotoSize thumb;
    private String fileName;
    private String mimeType;
    private int fileSize;

    public Document(JSONObject json) throws JSONException {
        super(json);

        fileId = JsonUtil.getString(json, CFields.FILE_ID);
        thumb = JsonUtil.getPhoto(json, CFields.THUMB);
        fileName = JsonUtil.getString(json, CFields.FILE_NAME);
        mimeType = JsonUtil.getString(json, CFields.MIME_TYPE);
        fileSize = JsonUtil.getInt(json, CFields.FILE_SIZE);
    }

    public String getFileId() {
        return fileId;
    }

    public PhotoSize getThumb() {
        return thumb;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public int getFileSize() {
        return fileSize;
    }
}
