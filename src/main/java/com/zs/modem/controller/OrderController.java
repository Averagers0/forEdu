package com.zs.modem.controller;

import com.zs.modem.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order1")
    public String order1(int a){
        return orderService.order123(a);
    }

    @GetMapping("/order2")
    public String order2(int a){
        return orderService.order123(a);
    }

    @GetMapping("/order3")
    public String order3(int a){
        return orderService.order123(a);
    }


    @GetMapping("/order4")
    public String order4(int a){
        return orderService.order123(a);
    }

   public void h() {

       System.out.println ("d");
   }
}
