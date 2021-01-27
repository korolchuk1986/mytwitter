package com.korolchuk1986.mytwitter.service;

import com.korolchuk1986.mytwitter.domain.Message;
import com.korolchuk1986.mytwitter.domain.User;
import com.korolchuk1986.mytwitter.repo.MessageRepo;
import com.korolchuk1986.mytwitter.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;


    public Page<Message> messagesList(Pageable pageable, String filter) {

        Page<Message> page;

        if(filter != null && !filter.isEmpty()) {
            page = messageRepo.findByTag(filter, pageable);
        } else {
            page = messageRepo.findAll(pageable);
        }
        return page;
    }

    public Page<Message> messagesListForUser(Pageable pageable,  User author) {
        return messageRepo.findByUser(pageable, author);
    }
}
