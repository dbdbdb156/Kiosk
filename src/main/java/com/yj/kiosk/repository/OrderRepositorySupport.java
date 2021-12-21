package com.yj.kiosk.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yj.kiosk.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.yj.kiosk.domain.QOrder.order;
import static com.yj.kiosk.domain.QMember.member;

@Repository
public class OrderRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Order.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        return jpaQueryFactory
                .selectFrom(order)
                .join(order.member,member)
                .where(eqStatus(orderSearch.getOrderStatus()),eqName(orderSearch.getMemberName()))
                .fetch();
    }

    private BooleanExpression eqName(String name) {
        return StringUtils.hasText(name) ? member.name.eq(name) : null;
    }

    private BooleanExpression eqStatus(OrderStatus orderStatus) {
        return orderStatus != null ? order.status.eq(orderStatus) : null ;
    }

}
