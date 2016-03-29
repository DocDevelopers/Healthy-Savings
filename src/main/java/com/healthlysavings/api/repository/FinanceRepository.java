package com.healthlysavings.api.repository;

import com.healthlysavings.api.domain.Finance;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by Doc on 3/15/16.
 */
public interface FinanceRepository extends CrudRepository<Finance, Long> {

    ArrayList<Finance> findByUserId(String userID);

    Finance findByUserIdAndDate(String userID, java.sql.Date date);

}
