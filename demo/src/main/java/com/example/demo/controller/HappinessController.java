package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Happiness;
import com.example.demo.services.HappinessService;

@RestController
public class HappinessController {
    @Autowired
    private HappinessService happinessService;

    @RequestMapping("/query/{name}")
    public Happiness testQuery(@PathVariable String name){
        return happinessService.selectService(name);
    }

}

