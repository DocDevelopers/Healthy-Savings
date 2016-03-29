package com.healthlysavings.api.controller;

import com.google.gson.Gson;
import com.healthlysavings.api.HealthySavingsApplication;
import com.healthlysavings.api.domain.BrainData;
import com.healthlysavings.api.repository.BrainDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * Created by simonhamermesh on 3/12/16.
 */
@RestController
public class BrainDataController {

    @Autowired
    private BrainDataRepository brainDataRepository;

    @RequestMapping("/get-braindata")
    @ResponseBody
    public String getBrainData(){

        Iterable<BrainData> brainDatas = brainDataRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(brainDatas);
    }

    @RequestMapping("/get-braindata-by-IdAndDate")
    @ResponseBody
    public int getBrainDataByIDAndDate(String userId, Date date){

        BrainData brainData = brainDataRepository.findByUserIdAndDate(userId,date);
        return brainData.getScore();
    }

    @RequestMapping("/create-braindata")
    @ResponseBody
    public void createBrainData(String userId, Date date, int score){

        try{BrainData brainData = new BrainData(userId,date,score);
            brainDataRepository.save(brainData);} catch(Exception e){
            Logger logger = LoggerFactory.getLogger(HealthySavingsApplication.class);
            logger.error("Error creating new BrainData record. " + e.toString() + e.getCause());
        }
    }
}