package com.jroot3d.telegram.core.utils;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.*;
import com.jroot3d.telegram.core.types.base.Chat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class JsonUtil {
    public static Integer getInt(JSONObject json, String key) throws JSONException {
        Integer result = null;
        if (json.has(key)) {
            result = json.getInt(key);
        }
        return result;
    }

    public static Boolean getBoolean(JSONObject json, String key) throws JSONException {
        Boolean result = null;
        if (json.has(key)) {
            result = json.getBoolean(key);
        }
        return result;
    }

    public static String getString(JSONObject json, String key) throws JSONException {
        String result = null;
        if (json.has(key)) {
            result = json.getString(key);
        }
        return result;
    }

    public static User getUser(JSONObject json, String key) throws JSONException {
        User result = null;
        if (json.has(key)) {
            result = new User(json.getJSONObject(key));
        }
        return result;
    }

    public static Chat getChat(JSONObject json, String key) throws JSONException {
        Chat result = null;
        if (json.has(CFields.CHAT)) {
            JSONObject chatObject = json.getJSONObject(CFields.CHAT);
            if (chatObject.has(CFields.TITLE)) {
                result = new GroupChat(chatObject);
            }

            if (chatObject.has(CFields.FIRST_NAME)) {
                result = new User(chatObject);
            }
        }
        return result;
    }

    public static Message getMessage(JSONObject json, String key) throws JSONException {
        Message result = null;
        if (json.has(key)) {
            result = new Message(json.getJSONObject(key));
        }
        return result;
    }


    public static ArrayList<Update> getUpdates(JSONObject json, String key) throws JSONException {
        ArrayList<Update> result = null;
        if (json.has(key)) {
            result = new ArrayList<>();
            JSONArray updates = json.getJSONArray(key);
            for (int i = 0; i < updates.length(); i++) {
                Update update = new Update(updates.getJSONObject(i));
                result.add(i, update);
            }
        }
        return result;
    }

    public static Audio getAudio(JSONObject json, String key) throws JSONException {
        Audio result = null;
        if (json.has(key)) {
            result = new Audio(json.getJSONObject(key));
        }
        return result;
    }

    public static Document getDocument(JSONObject json, String key) throws JSONException {
        Document result = null;
        if (json.has(key)) {
            result = new Document(json.getJSONObject(key));
        }
        return result;
    }

    public static ArrayList<PhotoSize> getPhotos(JSONObject json, String key) throws JSONException {
        ArrayList<PhotoSize> result = null;
        if (json.has(key)) {
            JSONArray photos = json.getJSONArray(key);
            result = new ArrayList<>();
            for (int i = 0; i < photos.length(); i++) {
                result.add(i, new PhotoSize(photos.getJSONObject(i)));
            }
        }
        return result;
    }

    public static PhotoSize getPhoto(JSONObject json, String key) throws JSONException {
        PhotoSize result = null;
        if (json.has(key)) {
            result = new PhotoSize(json.getJSONObject(key));
        }
        return result;
    }

    public static Sticker getSticker(JSONObject json, String key) throws JSONException {
        Sticker result = null;
        if (json.has(key)) {
            result = new Sticker(json.getJSONObject(key));
        }
        return result;
    }

    public static Video getVideo(JSONObject json, String key) throws JSONException {
        Video result = null;
        if (json.has(key)) {
            result = new Video(json.getJSONObject(key));
        }
        return result;
    }

    public static Contact getContact(JSONObject json, String key) throws JSONException {
        Contact result = null;
        if (json.has(key)) {
            result = new Contact(json.getJSONObject(key));
        }
        return result;
    }

    public static Location getLocation(JSONObject json, String key) throws JSONException {
        Location result = null;
        if (json.has(key)) {
            result = new Location(json.getJSONObject(key));
        }
        return result;
    }

    public static Double getFloat(JSONObject json, String key) throws JSONException {
        Double result = null;
        if (json.has(key)) {
            result = json.getDouble(key);
        }
        return result;
    }

    public static String[][] getStringArrays(JSONObject json, String key) throws JSONException {
        String[][] result = null;
        if (json.has(key)) {
            JSONArray arrays = json.getJSONArray(key);
            int arraysCount = arrays.length();
            result = new String[arraysCount][];
            for (int i = 0; i < arraysCount; i++) {
                JSONArray array = arrays.getJSONArray(i);
                int keysCount = array.length();
                String[] keys = new String[keysCount];
                for (int j = 0; j < keysCount; j++) {
                    keys[j] = array.getString(i);
                }
                result[i] = keys;
            }
        }
        return result;
    }
}
