package com.yj.kiosk.controller;

import com.yj.kiosk.domain.item.Item;
import com.yj.kiosk.service.ItemService;
import com.yj.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/order")
    public String readItems(Model model){
        List<Item> items = itemService.readItems();
        model.addAttribute("items",items);
        return "/orderItem";
    }
}
