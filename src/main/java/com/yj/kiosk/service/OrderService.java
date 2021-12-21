package com.yj.kiosk.service;

import com.yj.kiosk.domain.*;
import com.yj.kiosk.domain.item.Item;
import com.yj.kiosk.repository.ItemRepository;
import com.yj.kiosk.repository.OrderRepository;
import com.yj.kiosk.repository.OrderRepositorySupport;
import com.yj.kiosk.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderRepositorySupport orderRepositorySupport;

    @Transactional
    public Long order(String email,Long itemId, int count){

        // 엔티티 조회
        Member member = userRepository.findByEmail(email).get();
        Item item = itemRepository.findOne(itemId);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member,orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepositorySupport.findAllByString(orderSearch);
    }

}
