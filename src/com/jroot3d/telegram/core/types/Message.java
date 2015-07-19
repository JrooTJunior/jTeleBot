package com.jroot3d.telegram.core.types;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.types.base.Chat;
import com.jroot3d.telegram.core.types.base.TelegramType;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public class Message extends TelegramType {
    private int messageId;
    private User from;
    private Integer date;
    private Chat chat;
    private User forwardFrom;
    private Integer forwardDate;
    private Message replyToMessage;
    private String text;
    private Audio audio;
    private Document document;
    private ArrayList<PhotoSize> photo;
    private Sticker sticker;
    private Video video;
    private Contact contact;
    private Location location;
    private User newChatParticipant;
    private User leftChatParticipant;
    private String newChatTitle;
    private ArrayList<PhotoSize> newChatPhoto;
    private Boolean deleteChatPhoto;
    private Boolean groupChatCreated;

    public Message(JSONObject json) throws JSONException {
        super(json);

        messageId = JsonUtil.getInt(json, CFields.MESSAGE_ID);
        text = JsonUtil.getString(json, CFields.TEXT);
        date = JsonUtil.getInt(json, CFields.DATE);
        forwardDate = JsonUtil.getInt(json, CFields.FORWARD_DATE);
        deleteChatPhoto = JsonUtil.getBoolean(json, CFields.DELETE_CHAT_PHOTO);
        groupChatCreated = JsonUtil.getBoolean(json, CFields.GROUP_CHAT_CREATED);
        newChatTitle = JsonUtil.getString(json, CFields.NEW_CHAT_TITLE);

        from = JsonUtil.getUser(json, CFields.FROM);
        chat = JsonUtil.getChat(json, CFields.CHAT);
        forwardFrom = JsonUtil.getUser(json, CFields.FORWARD_FROM);
        replyToMessage = JsonUtil.getMessage(json, CFields.REPLY_TO_MESSAGE);
        audio = JsonUtil.getAudio(json, CFields.AUDIO);
        document = JsonUtil.getDocument(json, CFields.DOCUMENT);
        photo = JsonUtil.getPhotos(json, CFields.PHOTO);
        sticker = JsonUtil.getSticker(json, CFields.STICKER);
        video = JsonUtil.getVideo(json, CFields.VIDEO);
        contact = JsonUtil.getContact(json, CFields.CONTACT);
        location = JsonUtil.getLocation(json, CFields.LOCATION);
        newChatParticipant = JsonUtil.getUser(json, CFields.NEW_CHAT_PARTICIPANT);
        leftChatParticipant = JsonUtil.getUser(json, CFields.LEFT_CHAT_PARTICIPANT);
        newChatPhoto = JsonUtil.getPhotos(json, CFields.PHOTO);
    }

    public int getMessageId() {
        return messageId;
    }

    public User getFrom() {
        return from;
    }

    public int getDate() {
        return date;
    }

    public Chat getChat() {
        return chat;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public int getForwardDate() {
        return forwardDate;
    }

    public Message getReplyToMessage() {
        return replyToMessage;
    }

    public String getText() {
        return text;
    }

    public Audio getAudio() {
        return audio;
    }

    public Document getDocument() {
        return document;
    }

    public ArrayList<PhotoSize> getPhoto() {
        return photo;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public Video getVideo() {
        return video;
    }

    public Contact getContact() {
        return contact;
    }

    public Location getLocation() {
        return location;
    }

    public User getNewChatParticipant() {
        return newChatParticipant;
    }

    public User getLeftChatParticipant() {
        return leftChatParticipant;
    }

    public String getNewChatTitle() {
        return newChatTitle;
    }

    public ArrayList<PhotoSize> getNewChatPhoto() {
        return newChatPhoto;
    }

    public boolean isDeleteChatPhoto() {
        return deleteChatPhoto;
    }

    public boolean isGroupChatCreated() {
        return groupChatCreated;
    }
}
