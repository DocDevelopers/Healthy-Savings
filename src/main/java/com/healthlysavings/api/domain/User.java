package com.healthlysavings.api.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * Created by jrdavis on 3/14/16.
 */
@Entity
@Table(name = "users")
public class User {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // This is id the id in the style of CapitalOne API Customer's user_id. This is essentially the join key for every
    // other table.
    @Id
    private String id;


    // The user's first name
    @NotNull
    private String first_name;
    // The user's last name
    @NotNull
    private String last_name;


    // The user's address
    @NotNull
    private String city;
    @NotNull
    private String street_name;
    @NotNull
    private String zip;
    @NotNull
    private String state;
    @NotNull
    private String street_number;



    // The user's Gender
    @NotNull
    private String userGender;
    // The user's height
    @NotNull
    private Double userHeight;

    // The user's Weight
    @NotNull
    private Double userWeight;
    // the user's Third party app
    @NotNull
    private String thirdPartyChoice;
    // The user's email
    @NotNull
    private String email;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public User() { }

    //public User(String id) {this.id = id;}


    public User(CapitalOneCustomer c, String auser_Gender, Double auser_Height, Double auser_Weight, String athird_Party_Choice, String a_email){
        this.id = c.get_id();
        this.first_name = c.getFirst_name();
        this.last_name = c.getLast_name();
        this.city = c.getAddress().getCity();
        this.street_name = c.getAddress().getStreet_name();
        this.zip = c.getAddress().getZip();
        this.state = c.getAddress().getState();
        this.street_number = c.getAddress().getStreet_number();
        this.userGender = auser_Gender;
        this.userHeight = auser_Height;
        this.userWeight = auser_Weight;
        this.thirdPartyChoice = athird_Party_Choice;
        this.email = a_email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Double getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(Double userHeight) {
        this.userHeight = userHeight;
    }

    public Double getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(Double userWeight) {
        this.userWeight = userWeight;
    }

    public String getThirdPartyChoice() {
        return thirdPartyChoice;
    }

    public void setThirdPartyChoice(String thirdPartyChoice) {
        this.thirdPartyChoice = thirdPartyChoice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter methods


}