package com.korolchuk1986.mytwitter.controller;

import com.korolchuk1986.mytwitter.domain.Role;
import com.korolchuk1986.mytwitter.domain.User;
import com.korolchuk1986.mytwitter.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "userList";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String saveUser (
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);
        Set<String> rolesSet = new HashSet<>();

        for (Role role : Role.values()) {
            rolesSet.add(role.name());
        }
        user.getRoles().clear();

        for (String key : form.keySet()) {
            if(rolesSet.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
        return "redirect:/user";
    }


}
