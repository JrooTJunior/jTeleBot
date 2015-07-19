package com.jroot3d.telegram;

import com.goebl.david.Response;
import com.goebl.david.Webb;
import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.constants.CParams;
import com.jroot3d.telegram.core.types.Message;
import com.jroot3d.telegram.core.types.Update;
import com.jroot3d.telegram.core.types.User;
import com.jroot3d.telegram.core.types.UserProfilePhotos;
import com.jroot3d.telegram.core.types.base.ReplyMarkup;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class Bot {
    public static final String API_URL = "https://api.telegram.org/bot%s/";
    public static final String GET_ME = "getMe";
    public static final String GET_UPDATES = "getUpdates";
    public static final String SEND_MESSAGE = "sendMessage";

    private Webb webb;

    private User me;
    private boolean isUnauthorized = true;
    private boolean ok = false;
    private JSONObject response;

    public Bot(String token) {
        String baseUri = String.format(API_URL, token);
        webb = Webb.create();
        webb.setBaseUri(baseUri);
        getMe();
    }

    public User getMe() {
        response = webb.get(GET_ME)
                .ensureSuccess()
                .asJsonObject()
                .getBody();

        try {
            ok = JsonUtil.getBoolean(response, CFields.OK);

            if (ok) {
                isUnauthorized = false;
                me = JsonUtil.getUser(response, CFields.RESULT);
            } else {
                isUnauthorized = true;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return me;
    }

    public ArrayList<Update> getUpdates() {
        ArrayList<Update> updates = new ArrayList<>();
        if (!isUnauthorized) {
            response = webb.get(GET_UPDATES)
                    .ensureSuccess()
                    .asJsonObject()
                    .getBody();
            try {
                ok = JsonUtil.getBoolean(response, CFields.OK);

                if (ok) {
                    updates = JsonUtil.getUpdates(response, CFields.RESULT);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return updates;
    }

    public ArrayList<Update> getUpdates(int offset, int limit, int timeout) {
        ArrayList<Update> updates = new ArrayList<>();
        if (!isUnauthorized) {
            response = webb.get(GET_UPDATES)
                    .param(CParams.OFFSET, offset)
                    .param(CParams.LIMIT, limit)
                    .param(CParams.TIMEOUT, timeout)
                    .ensureSuccess()
                    .asJsonObject()
                    .getBody();
            try {
                ok = JsonUtil.getBoolean(response, CFields.OK);

                if (ok) {
                    updates = JsonUtil.getUpdates(response, CFields.RESULT);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return updates;
    }

    public ArrayList<Update> getUpdates(int offset) {
        ArrayList<Update> updates = new ArrayList<>();
        if (!isUnauthorized) {
            response = webb.post(GET_UPDATES)
                    .param(CParams.OFFSET, offset)
                    .ensureSuccess()
                    .asJsonObject()
                    .getBody();
            try {
                ok = JsonUtil.getBoolean(response, CFields.OK);

                if (ok) {
                    updates = JsonUtil.getUpdates(response, CFields.RESULT);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return updates;
    }

    public void setWebhook() {

    }

    public Message sendMessage(int chatId, String text) {
        Message message = null;
        if (!isUnauthorized) {
            try {
                Map<String, Object> params = new HashMap<>();
                params.put(CParams.CHAT_ID, chatId);
                params.put(CParams.TEXT, text);

                response = webb.post(SEND_MESSAGE)
                        .params(params)
                        .ensureSuccess()
                        .asJsonObject()
                        .getBody();

                ok = JsonUtil.getBoolean(response, CFields.OK);

                if (ok) {
                    message = JsonUtil.getMessage(response, CFields.RESULT);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public Message sendMessage(int chatId, String text, Boolean disableWebPagePreview, Integer replyToMessageId, ReplyMarkup replyMarkup) {
        Message message = null;
        if (!isUnauthorized) {
            try {
                Map<String, Object> params = new HashMap<>();
                params.put(CParams.CHAT_ID, chatId);
                params.put(CParams.TEXT, text);

                if (disableWebPagePreview != null) {
                    params.put(CParams.DISABLE_WEB_PAGE_PREVIEW, disableWebPagePreview);
                }

                if (replyToMessageId != null) {
                    params.put(CParams.REPLY_TO_MESSAGE_ID, replyToMessageId);
                }

                if (replyMarkup != null) {
                    params.put(CParams.REPLY_MARKUP, replyMarkup);
                }


                response = webb.get(GET_UPDATES)
                        .params(params)
                        .ensureSuccess()
                        .asJsonObject()
                        .getBody();

                ok = JsonUtil.getBoolean(response, CFields.OK);

                if (ok) {
                    message = JsonUtil.getMessage(response, CFields.RESULT);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public Message forvardMessage() {
        return null;
    }

    public Message sendPhoto() {
        return null;
    }

    public Message sendAudio() {
        return null;
    }

    public Message sendDocument() {
        return null;
    }

    public Message sendSticker() {
        return null;
    }

    public Message sendVideo() {
        return null;
    }

    public Message sendLocation() {
        return null;
    }

    public void sendChatAction() {

    }

    public UserProfilePhotos getUserProfilePhotos() {
        return null;
    }
}
