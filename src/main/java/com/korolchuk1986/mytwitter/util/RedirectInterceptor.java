package com.korolchuk1986.mytwitter.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null) {
            String args = "";
            if (request.getQueryString() != null) {
                args = request.getQueryString();
            }
            String url = request.getRequestURI().toString() + "?" + args;
            System.out.println(request.getRequestURI());
            response.setHeader("Turbolinks-Location", url);
        }
    }
}
