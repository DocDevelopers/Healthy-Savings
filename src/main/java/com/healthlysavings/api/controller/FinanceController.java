package com.healthlysavings.api.controller;

import com.healthlysavings.api.HealthySavingsApplication;
import com.healthlysavings.api.dao.FinanceDAO;
import com.healthlysavings.api.domain.Finance;
import com.healthlysavings.api.repository.FinanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Doc on 3/15/16.
 */

@RestController
public class FinanceController {

    @Autowired
    private FinanceRepository financeRepository;

    Logger logger = LoggerFactory.getLogger(HealthySavingsApplication.class);

    @RequestMapping("/finance/{user_id}")
    public ArrayList<Finance> getByUserID(@PathVariable(value="user_id") String id){
        return financeRepository.findByUserId(id);
    }

    @RequestMapping(value = "/finance/create-finance")
    public Finance createFinancePost(java.sql.Date date, String userId, int score){

        try{
            Finance newFinance = FinanceDAO.returnNewFinanceObject(date,userId,score);
            financeRepository.save(newFinance);
        }catch (Exception e){
            logger.error(e.toString());
            logger.debug(date + userId + score);
        }
        return null;
    }

}


