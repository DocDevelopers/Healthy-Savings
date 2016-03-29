package com.healthlysavings.api;

import com.healthlysavings.api.domain.User;
import com.healthlysavings.api.repository.BrainDataRepository;
import com.healthlysavings.api.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by simonhamermesh on 3/14/16.
 */

@Component
public class Scheduler {

    private static HashMap<String,String> thirdPartyChoiceStringMap = new HashMap<String, String>(){{
        put("GoogleFit","APIRequestURI");
        put("FitBit","APIRequestURI");
        put("Brain","APIRequestURI");
    }};

    private HashMap<String,String> userChoices;

    private Logger logger;

    @Autowired
    BrainDataRepository brainDataRepository;

    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = "53 29 * * * *")
    private void runDBUpdate(){

        setUserChoices();

        for(Map.Entry e : userChoices.entrySet()){
            try {
                String userId = e.getKey().toString();
                String currentChoice = e.getValue().toString();

                User user = new User();
                userRepository.save(user);

            } catch (Exception exc){
                logger.error("Error updating record for user " + e.getKey().toString());
            }
        }

    }

    private void setUserChoices(){

        /* TODO FINISH QUERYING ALL USERS FOR THEIR CURRENT CHOICE */
        for(User user: userRepository.findAll()){
            userChoices.put(user.getId(),user.getThirdPartyChoice());
        }


    }

    private void createNewFinancialRecord(String userId, int score){
        java.util.Date today = new java.util.Date();
        java.sql.Date sqltoday = new java.sql.Date(today.getTime());

        /*TODO CREATE A NEW FINANCIAL RECORD USING DATE,SCORE,AND USERID
           TODO - Finance newFinance = new Finance(userId,sqlToday,score)
            TODO - financeRepository.save(newFinance) */
    }
}
