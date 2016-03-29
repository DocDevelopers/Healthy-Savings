package com.healthlysavings.api.controller;

import com.google.gson.Gson;
import com.healthlysavings.api.HealthySavingsApplication;
import com.healthlysavings.api.domain.FitbitData;
import com.healthlysavings.api.repository.FitbitDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

//import java.sql.Date;

/**
 * Created by yash on 3/15/16.
 */
@RestController
public class Fitbit {

    @Autowired
    private FitbitDataRepository fitbitDataRepository;

    @RequestMapping("/get-fitbitdata")
    @ResponseBody
    public String getFitbitData(){

        Iterable<FitbitData> fitbitDatas = fitbitDataRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(fitbitDatas);
    }

    @RequestMapping("/get-fitbitdata-by-IdAndDate")
    @ResponseBody
    public int getFitbitDataByIDAndDate(String userId, Date date){

        FitbitData fitbitData = fitbitDataRepository.findByUserIdAndDate(userId,date);
        return fitbitData.getScore();
    }

    @RequestMapping("/create-fitbitdata")
    @ResponseBody
    public void createFitbitData(String userId, Date date, int score){

        try{FitbitData fitbitData = new FitbitData(userId,date,score);
            fitbitDataRepository.save(fitbitData);} catch(Exception e){
            Logger logger = LoggerFactory.getLogger(HealthySavingsApplication.class);
            logger.error("Error creating new FitbitData record. " + e.toString() + e.getCause());
        }
    }
}
