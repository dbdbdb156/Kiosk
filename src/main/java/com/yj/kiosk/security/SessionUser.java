package com.yj.kiosk.security;

import com.yj.kiosk.domain.Member;
import com.yj.kiosk.domain.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(Member member){
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}
