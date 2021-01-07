package com.korolchuk1986.mytwitter.controller;

import com.korolchuk1986.mytwitter.domain.Message;
import com.korolchuk1986.mytwitter.domain.User;
import com.korolchuk1986.mytwitter.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal  User user, Model model) {
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
    public String add(@AuthenticationPrincipal User user,
                      @Valid Message message,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException {

        message.setAuthor(user);

        if(bindingResult.hasErrors()) {
            model.mergeAttributes(UtilsController.getErrorsMap(bindingResult));
            model.addAttribute("message", message);
        } else {

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File fileDir = new File(uploadPath);

                if (!fileDir.exists()) {
                    fileDir.mkdir();
                }

                String uuid = UUID.randomUUID().toString();
                String resFilename = uuid + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resFilename));

                message.setFilename(resFilename);
            }

            model.addAttribute("message",null);
            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return  "main";
    }

}
