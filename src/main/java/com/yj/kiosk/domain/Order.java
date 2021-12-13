package com.yj.kiosk.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 쿼리 예약어 order by 랑 문제가 생길수 있음.
@Entity
@Table(name = "Orders")
@Setter
@Getter
public class Order {
    @Id
    @Column(name="order_id")
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime orderDate;

    // 상태는 숫자 인덱스가 아닌 String 으로 설정
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // ORDER,CANCEL 2가지 상태

    // ManyToOne 에 Lazy 로딩을 위함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 연관관계 메서드 : 테이블 일치
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member,OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

}
