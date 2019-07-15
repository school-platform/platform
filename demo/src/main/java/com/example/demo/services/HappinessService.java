package com.example.demo.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.HappinessMapper;
import com.example.demo.domain.Happiness;

@Service
public class HappinessService {
    @Autowired
    private HappinessMapper happinessmapper;

    public Happiness selectService(String city){
        return happinessmapper.selectByPrimaryKey(new Long(1));
    }
}


