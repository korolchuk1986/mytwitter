package com.korolchuk1986.mytwitter.controller;

import com.korolchuk1986.mytwitter.domain.Message;
import com.korolchuk1986.mytwitter.domain.User;
import com.korolchuk1986.mytwitter.domain.dto.MessageDto;
import com.korolchuk1986.mytwitter.repo.MessageRepo;
import com.korolchuk1986.mytwitter.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;


@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageService messageService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String home() {
        return  "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 8) Pageable pageable,
                       @AuthenticationPrincipal User user) {

        Page<MessageDto> page = messageService.messagesList(pageable,filter, user);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);
        return  "main";
    }
    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Message message,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file
                     ) throws IOException {

        message.setAuthor(user);

        if(bindingResult.hasErrors()) {
            model.mergeAttributes(UtilsController.getErrorsMap(bindingResult));
            model.addAttribute("message", message);
        } else {

            saveFile(message, file);

            model.addAttribute("message",null);
            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return  "redirect:/main";
    }

    private void saveFile(Message message, MultipartFile file) throws IOException {
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
    }

    @GetMapping("user-messages/{author}")
    public String userMessages(@AuthenticationPrincipal User currentUser,
                               @PathVariable User author,
                               @RequestParam(required = false) Message message,
                               Model model,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
        
        Page<MessageDto> page = messageService.messagesListForUser(pageable, currentUser, author);
        
        model.addAttribute("userChannel", author);
        model.addAttribute("subscriptionsCount", author.getSubscriptions().size());
        model.addAttribute("subscribersCount", author.getSubscribers().size());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("isSubcriber", author.getSubscribers().contains(currentUser));
        model.addAttribute("isCurrentUser", currentUser.equals(author));
        model.addAttribute("url", "/user-messages/" + author.getId());
        return "userMessages";
    }

    @PostMapping("user-messages/{user}")
    public String updateMessage(@AuthenticationPrincipal User currentUser,
                                @PathVariable User user,
                                @RequestParam("id") Message message,
                                @RequestParam("text") String text,
                                @RequestParam("tag") String tag,
                                @RequestParam("file") MultipartFile file) throws IOException {
        if(message.getAuthor().equals(currentUser)) {
            if(!StringUtils.isEmpty(text)) {
                message.setText(text);
            }
            if(!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }

            saveFile(message, file);

            messageRepo.save(message);
        }
        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("messages/{message}/like")
    public String like(@AuthenticationPrincipal User currentUser,
                       @PathVariable Message message,
                       RedirectAttributes redirectAttributes,
                       @RequestHeader(required = false) String referer) {
        Set<User> likes = message.getLikes();
        if(likes.contains(currentUser)) {
            likes.remove(currentUser);
        } else {
            likes.add(currentUser);
        }
        UriComponents components= UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams().
                entrySet().
                forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));
        return "redirect:" + components.getPath();
    }

}
