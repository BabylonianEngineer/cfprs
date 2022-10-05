package com.hf.cfprs.dao.mysql.tissue;

import com.hf.cfprs.pojo.mysql.tissue.TotalMeasureTissue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:hanfei
 */
@Repository
public interface TotalMeasureTissueRepository extends JpaRepository<TotalMeasureTissue,Long> {
}
