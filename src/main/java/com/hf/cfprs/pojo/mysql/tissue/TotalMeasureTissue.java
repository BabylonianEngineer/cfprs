package com.hf.cfprs.pojo.mysql.tissue;

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
public class TotalMeasureTissue {
    //表-11
    // (id,R名称,S名称,U名称,T名称,T合计,C项目编码,C项目名称,C金额,I项目编码,I项目名称,I计算基础,I费率,I金额,I调整费率,I调整后金额,I备注)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rName;
    private String sName;
    private String uName;
    private String tName;
    private Double tSummation;
    private String cProjectCode;
    private String cProjectName;
    private Double cAmount;
    private String iProjectCode;
    private String iProjectName;
    private String iCalculationBasis;
    private Double iRate;
    private Double iAmount;
    private Double iAdjustmentRate;
    private Double iAdjustedAmount;
    private String iRemark;

    public TotalMeasureTissue(String rName, String sName, String uName, String tName, Double tSummation, String cProjectCode, String cProjectName, Double cAmount,String iProjectCode,String iProjectName,String iCalculationBasis,Double iRate,Double iAmount,Double iAdjustmentRate,Double iAdjustedAmount,String iRemark) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tSummation = tSummation;
        this.cProjectCode = cProjectCode;
        this.cProjectName = cProjectName;
        this.cAmount = cAmount;
        this.iProjectCode=iProjectCode;
        this.iProjectName=iProjectName;
        this.iCalculationBasis=iCalculationBasis;
        this.iRate=iRate;
        this.iAmount=iAmount;
        this.iAdjustmentRate=iAdjustmentRate;
        this.iAdjustedAmount=iAdjustedAmount;
        this.iRemark=iRemark;

    }

}
