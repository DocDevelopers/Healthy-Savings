package com.healthlysavings.api.repository;

/**
 * Created by pwatson on 3/14/16.
 */

import com.healthlysavings.api.domain.GoogleFitData;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Date;


@Transactional
public interface GoogleFitRepository extends CrudRepository<GoogleFitData, String>{

        GoogleFitData findByUserIdAndDate(String userId, Date date);

        }