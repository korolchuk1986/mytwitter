package com.korolchuk1986.mytwitter.controller;

import com.korolchuk1986.mytwitter.domain.Message;
import com.korolchuk1986.mytwitter.domain.User;
import com.korolchuk1986.mytwitter.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @GetMapping("/")
    public String home(@AuthenticationPrincipal  User user, Model model) {
        String userName;
        if (user == null) {
            userName = "USER";
        } else {
            userName = user.getUsername();
        }
        model.addAttribute("user", userName);
        return  "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        if(filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return  "main";
    }
    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User author,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Model model) {
        Message message = new Message(text, tag, author);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return  "main";
    }
}
