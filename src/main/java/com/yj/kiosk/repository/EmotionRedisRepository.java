package com.yj.kiosk.repository;

import com.yj.kiosk.domain.Emotion;
import com.yj.kiosk.domain.Member;
import com.yj.kiosk.domain.Order;
import com.yj.kiosk.domain.item.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmotionRedisRepository extends CrudRepository<Emotion, String> {
    Integer countByItemName(String name);
    Integer countByUserName(String name);
    Optional<Emotion> findByUserName(String name);
}
