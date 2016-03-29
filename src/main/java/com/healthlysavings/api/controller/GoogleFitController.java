package com.healthlysavings.api.controller;

import com.google.gson.Gson;
import com.healthlysavings.api.domain.GoogleFitData;
import com.healthlysavings.api.repository.GoogleFitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * Created by pwatson on 3/14/16.
 */

@RestController
public class GoogleFitController {

    @Autowired
    private GoogleFitRepository googleFitRepository;

    @RequestMapping("/get-googleFit-data")
    @ResponseBody
    public String getGoogleFitData() {

        Iterable<GoogleFitData> dailyData = googleFitRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(dailyData);

    }

    @RequestMapping("/get-googleFit-data-by-IdAndDate")
    @ResponseBody
    public int getGoogleFitDataByIDandDate(String userId, Date date){

        GoogleFitData googleFitData = googleFitRepository.findByUserIdAndDate(userId,date);
        return googleFitData.getScore();
    }

    @RequestMapping("/create-googleFit_data")
    @ResponseBody
    public void createGoogleFitData(String userId, Date date, int score){

        GoogleFitData googleFitData = new GoogleFitData(userId,date,score);
        googleFitRepository.save(googleFitData);
    }

}
