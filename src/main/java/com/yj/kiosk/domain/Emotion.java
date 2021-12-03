package com.yj.kiosk.domain;

import com.yj.kiosk.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

// Redis DB 사용 예정
@Getter
@RedisHash("emotion")
public class Emotion {

    @Id // Redis 의 Key 는 String으로
    private String id;
    private String itemName;
    private String userName;

    @Builder
    public Emotion(String id, String itemName, String userName) {
        this.id = id;
        this.itemName = itemName;
        this.userName = userName;
    }
}
