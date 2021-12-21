package com.yj.kiosk.controller;

import com.yj.kiosk.dto.ItemEmotionDTO;
import com.yj.kiosk.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemEmotionController {
    private final EmotionService emotionService;

    @GetMapping("/items")
    public String itemlist(Model model){
        List<ItemEmotionDTO> items = emotionService.readItems();
        model.addAttribute("items",items);
        return "itemEmotionList";
    }
    @RequestMapping(value = "/items/{itemId}", method= {RequestMethod.GET, RequestMethod.POST})
    public String giveEmotion(@PathVariable("itemId") Long itemid, Model model){
        if(!emotionService.find(itemid)) emotionService.save(itemid);
        else emotionService.removeGood(itemid);
        return "redirect:/items";
    }

}
