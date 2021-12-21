package com.yj.kiosk.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemEmotionDTO {

    private Long id;
    private String name;
    private int price;
    private int stock;
    private Long count;
    private boolean choice;



}
