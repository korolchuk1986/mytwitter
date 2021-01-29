package com.korolchuk1986.mytwitter.domain.util;

import com.korolchuk1986.mytwitter.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author){
        if (author != null) {
            return author.getUsername();
        } else {
            return "<none>";
        }
    }
}
