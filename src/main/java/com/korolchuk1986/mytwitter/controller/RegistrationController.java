package com.korolchuk1986.mytwitter.controller;

import com.korolchuk1986.mytwitter.domain.User;
import com.korolchuk1986.mytwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("passwordError", "Passwords are different");
        }
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(UtilsController.getErrorsMap(bindingResult));
            return "/registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "/registration";
        }

        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        boolean isAcivated = userService.activateUser(code);

        if (isAcivated) {
            model.addAttribute("message", "User activated");
        } else {
            model.addAttribute("message", "User not activated");
        }
        return "/login";
    }
}
