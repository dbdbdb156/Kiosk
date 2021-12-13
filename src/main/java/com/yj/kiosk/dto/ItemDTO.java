package com.yj.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class ItemDTO {

    private Long id;
    private String name;
    private int price;

}
