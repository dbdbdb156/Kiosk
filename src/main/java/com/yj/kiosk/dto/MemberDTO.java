package com.yj.kiosk.dto;

import com.yj.kiosk.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class MemberDTO {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
}
