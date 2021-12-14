package com.yj.kiosk.controller;

import com.yj.kiosk.domain.Order;
import com.yj.kiosk.domain.OrderSearch;
import com.yj.kiosk.domain.OrderStatus;
import com.yj.kiosk.dto.ItemDTO;
import com.yj.kiosk.security.SessionUser;
import com.yj.kiosk.service.ItemService;
import com.yj.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final HttpSession httpSession;
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){

        List<ItemDTO> items = itemService.readItems();

        model.addAttribute("items",items);

        return "orderForm";
    }
    @PostMapping("/order")
    public String order(@RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){

        SessionUser user = (SessionUser) httpSession.getAttribute("member");

        orderService.order(user.getEmail(),itemId,count);
        return "redirect:/";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "orderList";
    }

}
