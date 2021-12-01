package com.yj.kiosk.domain.item;

import com.yj.kiosk.domain.Emotion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // 상속관계에서 하위 컬럼에 Dtype 생성
@Setter @Getter
public abstract class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue
    private Long id;

    private String name;

    private int stock;

    private int price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="emotion_id")
    private Emotion emotion;

}
