package com.zs.modem.Service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String order123(int a){
        if(a == 1){
            return "Pizza";
        } else if (a == 2) {
            return "noodles";
        }
        else{
            return "not";
        }
    }
}
