package com.jroot3d.telegram.core.types.base;

import com.jroot3d.telegram.core.constants.CFields;
import com.jroot3d.telegram.core.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oonufriychuk on 15.07.2015.
 */
public abstract class Chat extends TelegramType {
    private int id;

    public Chat(JSONObject json) throws JSONException {
        super(json);

        id = JsonUtil.getInt(json, CFields.ID);
    }

    public int getId() {
        return id;
    }
}


//TODO
//            if (chat instanceof User) {
//                System.out.println("User");
//                User u = (User) chat;
//                System.out.println(u.getUsername());
//            }
//
//            if (chat instanceof GroupChat) {
//                System.out.println("Group");
//                GroupChat g = (GroupChat) chat;
//                System.out.println(g.getTitle());
//            }
