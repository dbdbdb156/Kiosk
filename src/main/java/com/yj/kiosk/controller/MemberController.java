package com.yj.kiosk.controller;

import com.yj.kiosk.security.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public String start(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("member");
        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }



}
