package com.korolchuk1986.mytwitter.controller;

import com.korolchuk1986.mytwitter.domain.User;
import com.korolchuk1986.mytwitter.domain.dto.CaptchaResponseDto;
import com.korolchuk1986.mytwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {
    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(
            @Valid User user,
            @RequestParam("password2") String password2,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            BindingResult bindingResult,
            Model model) {

        String url = String.format(CAPTCHA_URL, recaptchaSecret, captchaResponse);

        CaptchaResponseDto captchaResponseDto = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        boolean captchaResponseDtoSuccess = captchaResponseDto.isSuccess();

        if(!captchaResponseDtoSuccess) {
            model.addAttribute("captchaError", "Fill captch");
        }

        boolean isEmptyPassword2 = StringUtils.isEmpty(password2);

        if(isEmptyPassword2) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }

        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            model.addAttribute("passwordError", "Passwords are different");
        }
        if (isEmptyPassword2 || bindingResult.hasErrors() || !captchaResponseDtoSuccess) {
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
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Activation code is not found");
            model.addAttribute("messageType", "danger");
        }
        return "/login";
    }
}
