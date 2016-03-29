package com.healthlysavings.api.repository;


import com.healthlysavings.api.domain.BrainData;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Date;

/**
 * Created by simonhamermesh on 3/12/16.
 */

@Transactional
public interface BrainDataRepository extends CrudRepository<BrainData,String>{

    BrainData findByUserId(String userId);
    BrainData findByUserIdAndDate(String userId, Date date);
    BrainData findByScore(int score);
    BrainData findByDate(Date date);
}
