package com.yj.kiosk.domain;

import com.yj.kiosk.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Redis DB 사용 예정
@Entity
@Getter
@Setter
public class Emotion {

    @Id
    @Column(name="emotion_id")
    @GeneratedValue
    private Long id;

    private int good;
    private int bad;

    @OneToOne(mappedBy = "emotion", fetch = FetchType.LAZY)
    private Item item;
}
