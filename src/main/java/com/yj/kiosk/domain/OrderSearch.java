package com.yj.kiosk.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Setter
@Getter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}

