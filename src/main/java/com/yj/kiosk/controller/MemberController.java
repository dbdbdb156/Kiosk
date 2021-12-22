package com.yj.kiosk.controller;

import com.yj.kiosk.dto.MemberDTO;
import com.yj.kiosk.security.SessionUser;
import com.yj.kiosk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final HttpSession httpSession;
    private final MemberService memberService;

    @GetMapping("/")
    public String start(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("member");
        if(user != null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("role",user.getRole());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/members")
    public String members(Model model){
        List<MemberDTO> members = memberService.findAll();
        model.addAttribute("members",members);
        return "memberList";
    }



}
