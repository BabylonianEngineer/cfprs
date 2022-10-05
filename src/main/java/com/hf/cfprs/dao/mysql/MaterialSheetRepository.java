package com.hf.cfprs.dao.mysql;

import com.hf.cfprs.pojo.mysql.MaterialSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:hanfei
 */
@Repository
public interface MaterialSheetRepository extends JpaRepository<MaterialSheet,Long> {
}
