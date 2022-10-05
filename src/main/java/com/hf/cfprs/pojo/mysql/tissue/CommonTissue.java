package com.hf.cfprs.pojo.mysql.tissue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author:hanfei
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "jpa-uuid",strategy = "uuid")
public class CommonTissue {
//    表-08
//    (id,R名称,S名称,U名称,T名称,T合价,T定额人工费,T暂估价,C名称,C合价,C定额人工费,C暂估价,I项目编码,I项目名称,I项目特征描述,I计量单位,I工程量,I综合单价,I合价,I定额人工费,I暂估价)

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String rName;
    private String sName;
    private String uName;
    private String tName;
    private Double tCombinedPrice;
    private Double tFixedLaborCost;
    private Double tProvisionalEstimate;
    private String cName;
    private Double cCombinedPrice;
    private Double cFixedLaborCost;
    private Double cProvisionalEstimate;
    private String iProjectCode;
    private String iProjectName;
    @Lob
    @Column(columnDefinition = "text")
    private String iProjectFeatureDescription;
    private String iMeasurementUnit;
    private Double iQuantity;
    private Double iComprehensiveUnitPrice;
    private Double iCombinedPrice;
    private Double iFixedLaborCost;
    private Double iProvisionalEstimate;

    public CommonTissue(String rName, String sName, String uName, String tName, Double tCombinedPrice, Double tFixedLaborCost, Double tProvisionalEstimate, String cName, Double cCombinedPrice, Double cFixedLaborCost, Double cProvisionalEstimate) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tCombinedPrice = tCombinedPrice;
        this.tFixedLaborCost = tFixedLaborCost;
        this.tProvisionalEstimate = tProvisionalEstimate;
        this.cName = cName;
        this.cCombinedPrice = cCombinedPrice;
        this.cFixedLaborCost = cFixedLaborCost;
        this.cProvisionalEstimate = cProvisionalEstimate;
    }

    public CommonTissue(String rName, String sName, String uName, String tName, Double tCombinedPrice, Double tFixedLaborCost, Double tProvisionalEstimate, String cName, Double cCombinedPrice, Double cFixedLaborCost, Double cProvisionalEstimate, String iProjectCode, String iProjectName, String iProjectFeatureDescription, String iMeasurementUnit, Double iQuantity, Double iComprehensiveUnitPrice, Double iCombinedPrice, Double iFixedLaborCost, Double iProvisionalEstimate) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tCombinedPrice = tCombinedPrice;
        this.tFixedLaborCost = tFixedLaborCost;
        this.tProvisionalEstimate = tProvisionalEstimate;
        this.cName = cName;
        this.cCombinedPrice = cCombinedPrice;
        this.cFixedLaborCost = cFixedLaborCost;
        this.cProvisionalEstimate = cProvisionalEstimate;
        this.iProjectCode = iProjectCode;
        this.iProjectName = iProjectName;
        this.iProjectFeatureDescription = iProjectFeatureDescription;
        this.iMeasurementUnit = iMeasurementUnit;
        this.iQuantity = iQuantity;
        this.iComprehensiveUnitPrice = iComprehensiveUnitPrice;
        this.iCombinedPrice = iCombinedPrice;
        this.iFixedLaborCost = iFixedLaborCost;
        this.iProvisionalEstimate = iProvisionalEstimate;
    }
}
