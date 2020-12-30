package com.korolchuk1986.mytwitter.controller;

import com.korolchuk1986.mytwitter.domain.Message;
import com.korolchuk1986.mytwitter.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @GetMapping("/")
    public String home() {
        return  "home";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return  "main";
    }
    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return  "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String tag, Map<String, Object> model) {
        Iterable<Message> messages;
        if(tag != null && !tag.isEmpty()) {
            messages = messageRepo.findByTag(tag);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);
        return  "main";
    }


}
