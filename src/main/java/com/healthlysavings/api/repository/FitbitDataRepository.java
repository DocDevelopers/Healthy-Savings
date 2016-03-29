package com.healthlysavings.api.repository;


import com.healthlysavings.api.domain.FitbitData;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Date;

/**
 * Created by yash on 3/15/16.
 */

@Transactional
public interface FitbitDataRepository extends CrudRepository<FitbitData,String>{

    FitbitData findByUserId(String userId);
    FitbitData findByUserIdAndDate(String userId, Date date);
    FitbitData findByScore(int score);
    FitbitData findByDate(Date date);
}
