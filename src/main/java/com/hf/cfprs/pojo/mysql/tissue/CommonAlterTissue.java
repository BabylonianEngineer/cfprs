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

public class CommonAlterTissue {
    //08改
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String rName;
    private String sName;
    private String uName;
    private String tName;
    private Double tComprehensiveCombinedPricePreTax;
    private Double tComprehensiveCombinedPriceIncludedTax;
    private String cName;
    private Double cComprehensiveCombinedPricePreTax;
    private Double cComprehensiveCombinedPriceIncludedTax;
    private String iCode;
    private String iName;
    @Lob
    @Column(columnDefinition = "text")
    private String iProjectFeature;
    @Lob
    @Column(columnDefinition = "text")
    private String iJobContent;
    @Lob
    @Column(columnDefinition = "text")
    private String iMeasurementRule;
    private String iUnit;
    private Double iQuantity;
    private Double iComprehensiveUnitPricePreTax;
    private Double iLaborCost;
    private Double iMainMaterialUnitPrice;
    private Double iMainMaterialDepletion;
    private Double iMainMaterialCost;
    private Double iAuxiliaryMaterialCost;
    private Double iMachiningCost;
    private Double iManagementCost;
    private Double iProfit;
    private Double iSpecifiedCost;
    private Double iTax;
    private Double iComprehensiveUnitPriceIncludedTax;
    private Double iComprehensiveCombinedPricePreTax;
    private Double iComprehensiveCombinedPriceIncludedTax;
    private String iRemark;
    private String iQuotationUnit;

    public CommonAlterTissue(String rName, String sName, String uName, String tName, Double tComprehensiveCombinedPricePreTax, Double tComprehensiveCombinedPriceIncludedTax, String cName, Double cComprehensiveCombinedPricePreTax, Double cComprehensiveCombinedPriceIncludedTax, String iCode, String iName, String iProjectFeature, String iJobContent, String iMeasurementRule, String iUnit, Double iQuantity, Double iComprehensiveUnitPricePreTax, Double iLaborCost, Double iMainMaterialUnitPrice, Double iMainMaterialDepletion, Double iMainMaterialCost, Double iAuxiliaryMaterialCost, Double iMachiningCost, Double iManagementCost, Double iProfit, Double iSpecifiedCost, Double iTax, Double iComprehensiveUnitPriceIncludedTax, Double iComprehensiveCombinedPricePreTax, Double iComprehensiveCombinedPriceIncludedTax, String iRemark, String iQuotationUnit) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tComprehensiveCombinedPricePreTax = tComprehensiveCombinedPricePreTax;
        this.tComprehensiveCombinedPriceIncludedTax = tComprehensiveCombinedPriceIncludedTax;
        this.cName = cName;
        this.cComprehensiveCombinedPricePreTax = cComprehensiveCombinedPricePreTax;
        this.cComprehensiveCombinedPriceIncludedTax = cComprehensiveCombinedPriceIncludedTax;
        this.iCode = iCode;
        this.iName = iName;
        this.iProjectFeature = iProjectFeature;
        this.iJobContent = iJobContent;
        this.iMeasurementRule = iMeasurementRule;
        this.iUnit = iUnit;
        this.iQuantity = iQuantity;
        this.iComprehensiveUnitPricePreTax = iComprehensiveUnitPricePreTax;
        this.iLaborCost = iLaborCost;
        this.iMainMaterialUnitPrice = iMainMaterialUnitPrice;
        this.iMainMaterialDepletion = iMainMaterialDepletion;
        this.iMainMaterialCost = iMainMaterialCost;
        this.iAuxiliaryMaterialCost = iAuxiliaryMaterialCost;
        this.iMachiningCost = iMachiningCost;
        this.iManagementCost = iManagementCost;
        this.iProfit = iProfit;
        this.iSpecifiedCost = iSpecifiedCost;
        this.iTax = iTax;
        this.iComprehensiveUnitPriceIncludedTax = iComprehensiveUnitPriceIncludedTax;
        this.iComprehensiveCombinedPricePreTax = iComprehensiveCombinedPricePreTax;
        this.iComprehensiveCombinedPriceIncludedTax = iComprehensiveCombinedPriceIncludedTax;
        this.iRemark = iRemark;
        this.iQuotationUnit = iQuotationUnit;
    }


}
