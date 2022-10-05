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
public class OtherTissue {
//    表-12
//(id,R名称,S名称,U名称,T名称,T合计,C项目名称,C金额,C结算金额,I项目名称,I金额,I结算金额,I备注)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rName;
    private String sName;
    private String uName;
    private String tName;
    private Double tSummation;
    private String cProjectName;
    private Double cAmount;
    private Double cSettlementAmount;
    private String cRemark;
    private String iProjectName;
    private Double iAmount;
    private Double iSettlementAmount;
    private String iRemark;

    public OtherTissue(String rName, String sName, String uName, String tName, Double tSummation, String cProjectName, Double cAmount, Double cSettlementAmount,String cRemark, String iProjectName, Double iAmount, Double iSettlementAmount, String iRemark) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tSummation = tSummation;
        this.cProjectName = cProjectName;
        this.cAmount = cAmount;
        this.cSettlementAmount = cSettlementAmount;
        this.cRemark=cRemark;
        this.iProjectName = iProjectName;
        this.iAmount = iAmount;
        this.iSettlementAmount = iSettlementAmount;
        this.iRemark = iRemark;

    }
}
