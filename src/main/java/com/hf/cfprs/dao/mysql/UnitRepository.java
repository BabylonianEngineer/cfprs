package com.hf.cfprs.dao.mysql;

import com.hf.cfprs.pojo.mysql.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:hanfei
 */
@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {

    @Query(nativeQuery = true,value = "select * from unit where r_name=?1 and s_name=?2 and u_name=?3")
    List<Unit> findByrNameAndsNameAnduName(String rName,String sName,String uName);
    @Query(nativeQuery = true,value = "select * from unit where r_name=?1 and s_name=?2 and u_name=?3 and t_name like ?4")
    List<Unit> findByrsuT(String rName, String sName, String uName,String tName);
}
