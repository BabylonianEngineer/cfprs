package com.hf.cfprs.dao.mysql;

import com.hf.cfprs.pojo.mysql.MainMaterialSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:hanfei
 */
@Repository
public interface MainMaterialSheetRepository extends JpaRepository<MainMaterialSheet,Long> {
}
