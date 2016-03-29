package com.healthlysavings.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Doc on 3/14/16.
 */

@Entity
public class Finance{

    /***** PRIVATE FIELDS *****/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private java.sql.Date date;

    @NotNull
    private String userId;

    @NotNull
    private double balance;

    @NotNull
    private double dailyAccrual;

    @NotNull
    private double bonusAPY;

    @NotNull
    private double bonusAccrual;


    /***** CONSTRUCTORS *****/

    public Finance(){}

    public Finance(Date date, String userId, double balance, double dailyAccrual, double bonusAPY, double bonusAccrual) {
        this.date = date;
        this.userId = userId;
        this.balance = balance;
        this.dailyAccrual = dailyAccrual;
        this.bonusAPY = bonusAPY;
        this.bonusAccrual = bonusAccrual;
    }


    /***** GETTERS AND SETTERS *****/

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDailyAccrual() {
        return dailyAccrual;
    }

    public void setDailyAccrual(double dailyAccrual) {
        this.dailyAccrual = dailyAccrual;
    }

    public double getBonusAPY() {
        return bonusAPY;
    }

    public void setBonusAPY(double bonusAPY) {
        this.bonusAPY = bonusAPY;
    }

    public double getBonusAccrual() {
        return bonusAccrual;
    }

    public void setBonusAccrual(double bonusAccrual) {
        this.bonusAccrual = bonusAccrual;
    }



}
