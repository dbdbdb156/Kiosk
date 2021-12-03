package com.yj.kiosk.repository;

import com.yj.kiosk.domain.Order;
import com.yj.kiosk.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }
    public void save(Item item){
        em.persist(item);
    }



}
