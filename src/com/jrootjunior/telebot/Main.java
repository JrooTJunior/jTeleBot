package com.jrootjunior.telebot;

import com.jroot3d.telegram.Bot;
import com.jroot3d.telegram.core.types.User;

public class Main {

    public static void main(String[] args) {
        if(args.length > 0) {
            // Get token..
            String token = args[0];
            // Create new bot
            Bot bot = new Bot(token);

            User userBot = bot.getMe();
            System.out.println("Connected. Bot Name: '" +
                    userBot.getFirstName() + "' @" + userBot.getUsername() + " (" + String.valueOf(userBot.getId()) + ")");

            // Do something here..
            
            // TODO: Add update listener
            // TODO: Add command handler
        }
    }
}
