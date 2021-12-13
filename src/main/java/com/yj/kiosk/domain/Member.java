package com.yj.kiosk.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Member{
    @Id
    @Column(name="member_id")
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    Member(String name, String email, String picture, Role role){
        this.name =name;
        this.email=email;
        this.picture=picture;
        this.role = role;
    }
    public Member update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }


    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
