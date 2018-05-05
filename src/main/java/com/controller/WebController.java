package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("name", name);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        System.out.println("----Session----");
        java.util.Enumeration<String> names = httpServletRequest.getSession().getAttributeNames();
        while (names.hasMoreElements()){
            String n = names.nextElement();
            System.out.println(n);
            System.out.println(httpServletRequest.getSession().getAttribute(n));
        }
        System.out.println("----Cookies----");
        for (javax.servlet.http.Cookie cookie : httpServletRequest.getCookies()) {
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
        }
        return "greeting";
    }

    @GetMapping("/greeting2")
    public String greeting2(HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        System.out.println("----Session----");
        java.util.Enumeration<String> names = httpServletRequest.getSession().getAttributeNames();
        while (names.hasMoreElements()){
            String n = names.nextElement();
            System.out.println(n);
            System.out.println(httpServletRequest.getSession().getAttribute(n));
        }
        System.out.println("----Cookies----");
        for (javax.servlet.http.Cookie cookie : httpServletRequest.getCookies()) {
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
        }
        return "greeting";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/detail")
    public String detail() {
        return "detail";
    }

}