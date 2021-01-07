package com.korolchuk1986.mytwitter.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class UtilsController {
    static public Map<String, String> getErrorsMap(BindingResult bindingResult) {
        Map<String, String> errorsMap = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorsMap.put(fieldError.getField() + "Error", fieldError.getDefaultMessage());
        }
        return errorsMap;
    }
}
