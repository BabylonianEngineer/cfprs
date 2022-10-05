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
public class MaterialSheet {
    //    表-21
//(id,R名称,S名称,U名称,T名称,I描述,I单位,I数量,I风险系数,I基准单价,I投标单价,I发承包人确认单价,I备注)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rName;
    private String sName;
    private String uName;
    private String tName;
    private String iDescription;
    private String iUnit;
    private Double iCount;
    private Double iRiskFactor;
    private Double iBenchmarkUnitPrice;
    private Double iBidUnitPrice;
    private Double iContractorToConfirmUnitPrice;
    private String iRemark;

    public MaterialSheet(String rName, String sName, String uName, String tName, String iDescription, String iUnit, Double iCount, Double iRiskFactor, Double iBenchmarkUnitPrice, Double iBidUnitPrice, Double iContractorToConfirmUnitPrice, String iRemark) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.iDescription = iDescription;
        this.iUnit = iUnit;
        this.iCount = iCount;
        this.iRiskFactor = iRiskFactor;
        this.iBenchmarkUnitPrice = iBenchmarkUnitPrice;
        this.iBidUnitPrice = iBidUnitPrice;
        this.iContractorToConfirmUnitPrice = iContractorToConfirmUnitPrice;
        this.iRemark = iRemark;
    }
}
