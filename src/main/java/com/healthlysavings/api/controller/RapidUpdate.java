package com.healthlysavings.api.controller;

import com.healthlysavings.api.HealthySavingsApplication;
import com.healthlysavings.api.dao.FinanceDAO;
import com.healthlysavings.api.domain.*;
import com.healthlysavings.api.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * Created by yeshpal on 3/29/16.
 */
@RestController
public class RapidUpdate {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FitbitDataRepository fitbitDataRepository;
    @Autowired
    private FinanceRepository financeRepository;
    @Autowired
    private GoogleFitRepository googleFitRepository;
    @Autowired
    private BrainDataRepository brainDataRepository;
    @RequestMapping("/rapid-update")
    @ResponseBody
    public String rapidUpdateAcivityData(String userId) {

        Logger logger = LoggerFactory.getLogger(HealthySavingsApplication.class);

        String thirdPartyChoice = null;
        Date date = null;
        int score = 0;
        User user = userRepository.findById(userId);
        thirdPartyChoice = user.getThirdPartyChoice();
        if (thirdPartyChoice.equals("fitbit")) {
            FitbitData fitbitData = fitbitDataRepository.findByUserId(userId);
            date = fitbitData.getDate();
            score = fitbitData.getScore();
        }
        if (thirdPartyChoice.equals("googlefit")) {
            GoogleFitData googleFitData = googleFitRepository.findOne(userId);
            date = googleFitData.getDate();
            score = googleFitData.getScore();
        }
        if (thirdPartyChoice.equals("braindata")) {
            BrainData brainData = brainDataRepository.findByUserId(userId);
            date = brainData.getDate();
            score = brainData.getScore();
        }
        try {
            Finance newFinance = FinanceDAO.returnNewFinanceObject(date, userId, score);
            financeRepository.save(newFinance);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(date + userId + score);
        }

        return null;
    }
}
