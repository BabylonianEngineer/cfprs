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
public class TaxTissue {
//    表-13
//(id,R名称,S名称,U名称,T名称,T合计,C项目名称,C计算基础,C计算基数,C计算费率,C金额,I项目名称,I计算基础,I计算基数,I计算费率,I金额)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rName;
    private String sName;
    private String uName;
    private String tName;
    private Double tSummation;
    private String cProjectName;
    private String cCalculationBasis;
    private Double cCalculationCardinality;
    private Double cCalculationRate;
    private Double cAmount;
    private String iProjectName;
    private String iCalculationBasis;
    private Double iCalculationCardinality;
    private Double iCalculationRate;
    private Double iAmount;

    public TaxTissue(String rName, String sName, String uName, String tName, Double tSummation, String cProjectName, String cCalculationBasis, Double cCalculationCardinality, Double cCalculationRate, Double cAmount, String iProjectName, String iCalculationBasis, Double iCalculationCardinality, Double iCalculationRate, Double iAmount) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tSummation = tSummation;
        this.cProjectName = cProjectName;
        this.cCalculationBasis = cCalculationBasis;
        this.cCalculationCardinality = cCalculationCardinality;
        this.cCalculationRate = cCalculationRate;
        this.cAmount = cAmount;
        this.iProjectName = iProjectName;
        this.iCalculationBasis = iCalculationBasis;
        this.iCalculationCardinality = iCalculationCardinality;
        this.iCalculationRate = iCalculationRate;
        this.iAmount = iAmount;
    }

}
