package com.yj.kiosk.service;

import com.yj.kiosk.domain.item.Item;
import com.yj.kiosk.dto.ItemDTO;
import com.yj.kiosk.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemDTO> readItems(){
        List<Item> itemlist = itemRepository.findAll();
        List<ItemDTO> items = new ArrayList<>();
        for(Item item : itemlist){
            items.add(new ItemDTO(item.getId(),item.getName(),item.getPrice()));
        }

        return items;
    }



}
