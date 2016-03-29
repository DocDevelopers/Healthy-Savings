package com.healthlysavings.api.controller;



/**
 * Created by jrdavis on 3/14/16.
 */

import com.healthlysavings.api.dao.FinanceDAO;
import com.healthlysavings.api.domain.CapitalOneCustomer;
import com.healthlysavings.api.domain.Finance;
import com.healthlysavings.api.domain.User;
import com.healthlysavings.api.repository.FinanceRepository;
import com.healthlysavings.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jrdavis on 3/7/16.
 */



@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FinanceRepository financeRepository;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /***** USER CREATE METHODS *****/

    /* TODO - Determine strategies for new USER creation.
    *  New Users should be created by an "administrator". When a new User
    *  is created, an entire User object should be persisted, as well as
    *  a Finance object representing their "opening balance".
    *
    *  The question is how much integration with the CapitalOneAPI
    *  we will be doing.  One way to do this would be to route the
    *  initial User creation to the CapOneAPI POST new Customer
    *  route and new Account route. Then query the CapOneAPI for the customer
    *  matching the id from the ResponseBody from the successful creation.
    *  Then create a new account to CapOneAPI using this new id and create
    *  a balance, etc.
    *
    *  After the CapOneAPI is updated FIRST with the new User info,
    *  then the local database will call this and create a new User
    *  object and Finance Object.
    *
    *  For now... the create method can take all the pertinents and perform our
    *  local functions without integrating CapOneAPI.
    *
    *
    * */


    @RequestMapping(value ="/user/create")
    @ResponseBody
    public String create(String lastName, String firstName, String city,
                         String streetName, String zip,
                         String state, String streetNumber,
                         String email, Double userHeight,
                         Double userWeight, String ThirdPartyChoice,
                         String userGender, double balance)
    {

        User user;
        Finance finance;

        try {
       /*** TODO THIS IS WHERE CAPONE USER CREATION WOULD GO.  DO THIS BEFORE CREATING A NEW USER OBJECT LOCALLY ***/
            CapitalOneCustomer capitalOneCustomer = null;
        /* TODO FILL THIS IN */

            user = new User(capitalOneCustomer, email, userHeight, userWeight, ThirdPartyChoice, userGender);
            userRepository.save(user);
            finance = FinanceDAO.firstFinanceRecordForNewUser(user.getId(),balance);
            financeRepository.save(finance);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }


    /***** USER READ METHODS *****/

    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId;
        String userName;
        try {
            User user = userRepository.findByEmail(email);
            userId = String.valueOf(user.getId());
            userName = String.valueOf(user.getFirst_name());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId + "user name: " + userName;
    }

    @RequestMapping("/get-by-id")
    @ResponseBody
    public String getByID(String id) {

        String userEmail;
        String userName;
        try {
            User user = userRepository.findById(id);
            userEmail = String.valueOf(user.getEmail());
            userName = String.valueOf(user.getFirst_name());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user email is: " + userEmail+ "user name: " + userName;
    }


    /***** USER UPDATE METHODS *****/

    /***** There can be a whole host of these or one fairly complex one. The idea is that updating user information should be able to selectively
     * update attributes of a User.  For now, the most important one of these update methods is the updateThirdParty method. This
     * if going to be used by the front-end.  The rest of the updating methods can be made as extra features if and when.
     * *****/

    @RequestMapping("/user/update")
    @ResponseBody
    public String updateUser(String id, String email, String third_party, String first_name, String last_name,  String userGender, Double userHeight, Double userWeight) {
        try {
            User user = userRepository.findOne(id);
            user.setEmail(email);
            user.setThirdPartyChoice(third_party);
            user.setFirst_name(first_name);
            user.setLast_name(last_name);
            user.setUserWeight(userWeight);
            user.setUserHeight(userHeight);
            user.setUserGender(userGender);
            userRepository.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    @RequestMapping("/user/updatethirdparty")
    @ResponseBody
    public String updateThirdParty(String userId, String third_party) {
        try {
            User user = userRepository.findOne(userId);
            user.setThirdPartyChoice(third_party);
            userRepository.save(user);
        }
        catch (Exception ex) {
            return "Error updating the third party application: " + ex.toString();
        }
        return "Third Party Application successfully updated! Your new ThirdParty is: " + userRepository.findOne(userId).getThirdPartyChoice();
    }


    /***** USER DELETE METHODS *****/

    @RequestMapping("/user/delete")
    @ResponseBody
    public String delete(String userId) {
        try {
            userRepository.delete(userRepository.findById(userId));
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }




}