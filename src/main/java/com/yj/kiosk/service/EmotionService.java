package com.yj.kiosk.service;

import com.yj.kiosk.domain.Emotion;
import com.yj.kiosk.domain.Member;
import com.yj.kiosk.domain.item.Item;
import com.yj.kiosk.repository.EmotionRedisRepository;
import com.yj.kiosk.repository.ItemRepository;
import com.yj.kiosk.security.SessionUser;
import com.yj.kiosk.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmotionService {

    private final EmotionRedisRepository emotionRepository;
    private final ItemRepository itemRepository;
    private HttpSession httpSession;

    /*
    # 기능
    1. 상품 좋아요 실시간 확인
    2. 상품 좋아요 등록 및 취소 가능
    */
    @Transactional(readOnly = true)
    public int getGoodByItemName(Long itemid){
        return emotionRepository.countByItemName(itemRepository.findOne(itemid).getName());
    }

    public void saveGood(Long itemid){
        SessionUser user = (SessionUser) httpSession.getAttribute("member");
        String userName = user.getName();
        String itemName = itemRepository.findOne(itemid).getName();
        emotionRepository.save(new Emotion(null,itemName,userName));
    }

    public void removeGood(){
        SessionUser user = (SessionUser) httpSession.getAttribute("member");
        Optional<Emotion> byUserName = emotionRepository.findByUserName(user.getName());
        emotionRepository.delete(byUserName.stream().findAny().get());
    }


}
