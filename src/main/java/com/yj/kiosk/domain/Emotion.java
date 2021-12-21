package com.yj.kiosk.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

// Redis DB 사용 예정
//
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emotion implements Serializable {

    @Id // Redis 의 Key 는 String으로
    private String id;
    private String count;

    @Builder
    public Emotion(String id, String count) {
        this.id = id;
        this.count = count;
    }
}
