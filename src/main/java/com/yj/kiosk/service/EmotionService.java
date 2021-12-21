package com.yj.kiosk.service;

import com.yj.kiosk.domain.item.Item;
import com.yj.kiosk.dto.ItemEmotionDTO;
import com.yj.kiosk.repository.ItemRepository;
import com.yj.kiosk.repository.MemberRepository;
import com.yj.kiosk.security.SessionUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional

public class EmotionService {

    public static final String ITEM = "item:";

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final HttpSession httpSession;
    private final StringRedisTemplate redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
    # 기능
    1. 상품 좋아요 실시간 확인
    2. 상품 좋아요 등록 및 취소 가능
    */

    // 속도를 위해 양방향 테이블 설계
    // 좋아요에 상품 저장
    // 1. 상품별로 인기순 저장
    // 2. 유저별로 저장
    public void save(Long itemid){
        SessionUser user = (SessionUser) httpSession.getAttribute("member");
        Long userid = memberRepository.findByName(user.getName()).getId();

        // 아이템 아이디에 저장
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        // 현재유저가 좋아요를 했는지 판별
        // item에 속한 종아요 갯수 필요
        setOperations.add(ITEM+itemid,userid.toString());
    }
    public void removeGood(Long itemid){
        SessionUser user = (SessionUser) httpSession.getAttribute("member");
        Long userid = memberRepository.findByName(user.getName()).getId();

        // 아이템 아이디에 저장
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        // 현재유저가 좋아요를 했는지 수 제거
        // item에 속한 종아요 갯수 제거
        setOperations.remove(ITEM+itemid,userid.toString());
    }
    public boolean find(Long itemid){
        SessionUser user = (SessionUser) httpSession.getAttribute("member");
        Long userid = memberRepository.findByName(user.getName()).getId();
        return redisTemplate.opsForSet().isMember(ITEM+itemid,userid.toString());
    }
    // 상품을 읽으며 현재 사용 유저가 좋아요를 상품에 눌렀는지 확인 해줌
    public List<ItemEmotionDTO> readItems(){
        SessionUser user = (SessionUser) httpSession.getAttribute("member");
        Long userid = memberRepository.findByName(user.getName()).getId();

        List<Item> itemlist = itemRepository.findAll();
        List<ItemEmotionDTO> items = new ArrayList<>();

        Set<String> userkeys = redisTemplate.keys(ITEM+"*");

        for(Item item : itemlist){
            logger.info("item id의 값 : {}",item.getId());
            // 유저가 있다면
            boolean check = redisTemplate.opsForSet().isMember(ITEM+item.getId(),userid.toString()) ? true : false ;
            logger.info("item의 사이즈 : {}",redisTemplate.opsForSet().size(ITEM+item.getId()));
            logger.info("유저가 있나 : {}",check);

            // ITEMEmotionDTO 생성
            items.add(new ItemEmotionDTO(item.getId(),item.getName(),item.getPrice(),item.getStock(),redisTemplate.opsForSet().size(ITEM+item.getId()),check));
        }
        return items;
    }


}
