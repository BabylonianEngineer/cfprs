package com.hf.cfprs.pojo.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author:hanfei
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MainMaterialSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rName;
    private String sName;
    private String uName;
    private String tName;
    private String iMaterialName;
    private String iDescription;
    private String iUnit;
    private Double iUnitPrice;
    private String iRemark;

    public MainMaterialSheet(String rName, String sName, String uName, String tName, String iMaterialName, String iDescription, String iUnit, Double iUnitPrice, String iRemark) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.iMaterialName = iMaterialName;
        this.iDescription = iDescription;
        this.iUnit = iUnit;
        this.iUnitPrice = iUnitPrice;
        this.iRemark = iRemark;
    }


}
