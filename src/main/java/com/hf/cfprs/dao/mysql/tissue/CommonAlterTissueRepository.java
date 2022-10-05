package com.hf.cfprs.dao.mysql.tissue;

import com.hf.cfprs.pojo.mysql.tissue.CommonAlterTissue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:hanfei
 */
@Repository
public interface CommonAlterTissueRepository extends JpaRepository<CommonAlterTissue,Long> {
}
