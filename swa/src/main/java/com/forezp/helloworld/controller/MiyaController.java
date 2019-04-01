package com.forezp.helloworld.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Pandy
 * @Date: 2019/3/30 21:53
 * @Version 1.0
 */
@RestController
public class MiyaController {
    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private int age;

    @RequestMapping("/hello2")
    public String miya(){
        System.out.println("-------------------------->");
        return name +":" + age;
    }
}
