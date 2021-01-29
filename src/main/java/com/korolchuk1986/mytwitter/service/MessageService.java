package com.korolchuk1986.mytwitter.service;

import com.korolchuk1986.mytwitter.domain.User;
import com.korolchuk1986.mytwitter.domain.dto.MessageDto;
import com.korolchuk1986.mytwitter.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;


    public Page<MessageDto> messagesList(Pageable pageable, String filter, User user) {

        Page<MessageDto> page;

        if(filter != null && !filter.isEmpty()) {
            page = messageRepo.findByTag(filter, pageable, user);
        } else {
            page = messageRepo.findAll(pageable, user);
        }
        return page;
    }

    public Page<MessageDto> messagesListForUser(Pageable pageable,  User currentUser,  User author) {
        return messageRepo.findByUser(pageable, author, currentUser);
    }
}
