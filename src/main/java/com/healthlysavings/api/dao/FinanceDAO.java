package com.healthlysavings.api.dao;

import com.healthlysavings.api.domain.Finance;
import com.healthlysavings.api.repository.FinanceRepository;
import com.healthlysavings.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by simonhamermesh on 3/21/16.
 */

public class FinanceDAO {

    @Autowired
    static FinanceRepository financeRepository;
    @Autowired
    static UserRepository userRepository;

    private static Date date;
    private static String userId;

    private static int score;

    private static double balance;

    private static double baseAPY = 0.6;
    private static double dailyAccrual;

    private static double scoreFactor;
    private static double maxBonusAPY = 0.6;

    private static double bonusAPY;
    private static double bonusAccrual;


    public static Finance firstFinanceRecordForNewUser(String userId, double balance){

        java.util.Date today = new java.util.Date();
        java.sql.Date sqltoday = new java.sql.Date(today.getTime());

        return new Finance(sqltoday, userId, balance,0,0,0);
    }

    public static Finance returnNewFinanceObject(java.sql.Date idate, String iuserId, int iscore){

        date = idate;
        userId = iuserId;
        score = iscore;

        makeBalance();
        makeDailyAccrual();
        makeBonus();

        return new Finance(date,userId,balance,dailyAccrual,bonusAPY,bonusAccrual);
    }

    private static void makeBalance(){

        /**
         * Set the balance as the previous day's balance. For now the first Finance Object for each User will be created on new User creation.
         * In the future, the balance will be updated at the end of the month to reflect monthly compounding.
         * **/

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        java.sql.Date yesterday = new java.sql.Date(calendar.getTime().getTime());


        balance = financeRepository.findByUserIdAndDate(userId,yesterday).getBalance();
    }

    private static void makeDailyAccrual(){
        dailyAccrual = (baseAPY/365) * balance;
    }

    private static void makeBonus(){
        calculateScoreFactor();

        /***  THIS IS THE CALCULATION FOR THE DAILY BONUS APY.  THE FIRST FACTOR IS THE SCORE FACTOR. ASSUMING AN AVERAGE
         *  BMI OF 20, THIS FACTOR WILL BE 1.  IF THE PERSON IS OBESE AT A BMI OF 30, THIS FACTOR WILL BE 0.6.
         *  THIS IS THE LIMITING FACTOR OF THE maxBonusAPY.
         *
         *  THE SECOND FACTOR IS FROM THEIR DAILY EXERCISE SCORE.  IF THEIR DAILY SCORE IS THE MAX 100, THIS FACTOR WILL BE 1.
         *  IF THEIR DAILY EXERCISE IS A SCORE OF 65, THIS FACTOR WILL BE 0.65.
         *
         *  EXAMPLE:  PERSON WITH BMI 25 (slightly overweight) WHO EXERCISED TO %70 OF REQUIRED DAILY EXERCISE:
         *
         *  ((20/25) * 0.6 * (70/100)) = 0.8 * 0.6 * 0.7 = 0.34  OUT OF A MAX 0.6
         *
         * ***/
        bonusAPY = ((20/scoreFactor) * maxBonusAPY) * (score/100);

        bonusAccrual = (bonusAPY/365) * balance;
    }

    private static void calculateScoreFactor(){
        scoreFactor = (userRepository.findById(userId).getUserWeight() /
                (userRepository.findById(userId).getUserHeight()*userRepository.findById(userId).getUserHeight())) * 703;

        /** THIS IS THE BMI FORMULA
         *
         *  AVERAGE WILL BE ABOUT 20.  OBESE PEOPLE ARE ABOUT 30.  THIS MEANS THE HIGHER THE SCORE FACTOR...
         *  THE LOWER THE BONUS APY SHOULD BE.
         *
         */
    }

}
