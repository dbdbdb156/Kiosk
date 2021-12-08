package com.yj.kiosk.service;

import com.yj.kiosk.domain.item.Item;
import com.yj.kiosk.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> readItems(){
        return itemRepository.findAll();
    }



}
