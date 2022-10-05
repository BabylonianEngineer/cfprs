package com.hf.cfprs.dao.mysql;

import com.hf.cfprs.pojo.mysql.Single;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:hanfei
 */
@Repository
public interface SingleRepository extends JpaRepository<Single, Long> {
    List<Single> findByrName(String rName);

    @Query(nativeQuery = true, value = "select * from single where r_name=?1 and s_name=?2")
    List<Single> findByrNameAndsName(String rName, String sName);

}
