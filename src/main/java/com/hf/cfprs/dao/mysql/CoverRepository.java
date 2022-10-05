package com.hf.cfprs.dao.mysql;

import com.hf.cfprs.pojo.mysql.Cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:hanfei
 */
@Repository
public interface CoverRepository extends JpaRepository<Cover,Long> {
    List<Cover> findByrName(String rName);
}
