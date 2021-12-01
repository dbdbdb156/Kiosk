package com.yj.kiosk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Member {
    @Id
    @Column(name="member_id")
    @GeneratedValue
    private Long id;

    private String name;




    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
