package com.yj.kiosk.domain;

import com.yj.kiosk.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Order_Item")
@Setter
@Getter
public class OrderItem {
    @Id
    @Column(name = "order_item_id")
    @GeneratedValue
    private Long id;

    @Column
    private int count;

    @Column
    private int sumPrice;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static OrderItem createOrderItem(Item item, int sumPrice,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setSumPrice(sumPrice);
        orderItem.setCount(count);

        item.removeQuantity(count);
        return orderItem;
    }



}
