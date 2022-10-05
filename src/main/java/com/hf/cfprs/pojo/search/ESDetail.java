package com.hf.cfprs.pojo.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHit;

/**
 * @Author:hanfei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ESDetail {
    private String id;
    private String rName;
    private String sName;
    private String uName;
    private String tName;
    private Double tComprehensiveCombinedPricePreTax;
    private Double tComprehensiveCombinedPriceIncludedTax;
    private String iCode;
    private String iName;
    private String iFeature;
    private String iJobContent;
    private String iMeasurementRule;
    private String iSupplyWay;
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
    private Double iPrice;
    private String iRemark;
    private String iQuotationUnit;
    private String cName;
    private Double cComprehensiveCombinedPricePreTax;
    private Double cComprehensiveCombinedPriceIncludedTax;
    private Double tCombinedPrice;
    private Double tFixedLaborCost;
    private Double tProvisionalEstimate;
    private Double cCombinedPrice;
    private Double cFixedLaborCost;
    private Double cProvisionalEstimate;
    private String iFeatureDescription;
    private String iMeasurementUnit;
    private Double iComprehensiveUnitPrice;
    private Double iFixedLaborCost;
    private Double iProvisionalEstimate;
    private String iDescription;
    private Double tSummation;
    private Double cAmount;
    private Double cSettlementAmount;
    private String cRemark;
    private Double iSettlementAmount;
    private String cCalculationBasis;
    private Double cCalculationCardinality;
    private Double cCalculationRate;
    private String iCalculationBasis;
    private Double iCalculationCardinality;
    private Double iCalculationRate;
    private String cCode;
    private Double iRate;
    private Double iAdjustmentRate;
    private Double iAdjustedAmount;

    public ESDetail(SearchHit<ESDetail> searchHit) {
        this.id = searchHit.getContent().getId();
        this.rName = searchHit.getContent().getRName();
        this.sName = searchHit.getContent().getSName();
        this.uName = searchHit.getContent().getUName();
        this.tName = searchHit.getContent().getTName();
        this.tComprehensiveCombinedPricePreTax = searchHit.getContent().getTComprehensiveCombinedPricePreTax();
        this.tComprehensiveCombinedPriceIncludedTax = searchHit.getContent().getTComprehensiveCombinedPriceIncludedTax();
        this.iCode = searchHit.getContent().getICode();
        this.iName = searchHit.getContent().getIName();
        this.iFeature = searchHit.getContent().getIFeature();
        this.iJobContent = searchHit.getContent().getIJobContent();
        this.iMeasurementRule = searchHit.getContent().getIMeasurementRule();
        this.iSupplyWay = searchHit.getContent().getISupplyWay();
        this.iUnit = searchHit.getContent().getIUnit();
        this.iQuantity = searchHit.getContent().getIQuantity();
        this.iComprehensiveUnitPricePreTax = searchHit.getContent().getIComprehensiveUnitPricePreTax();
        this.iLaborCost = searchHit.getContent().getILaborCost();
        this.iMainMaterialUnitPrice = searchHit.getContent().getIMainMaterialUnitPrice();
        this.iMainMaterialDepletion = searchHit.getContent().getIMainMaterialDepletion();
        this.iMainMaterialCost = searchHit.getContent().getIMainMaterialCost();
        this.iAuxiliaryMaterialCost = searchHit.getContent().getIAuxiliaryMaterialCost();
        this.iMachiningCost = searchHit.getContent().getIMachiningCost();
        this.iManagementCost = searchHit.getContent().getIManagementCost();
        this.iProfit = searchHit.getContent().getIProfit();
        this.iSpecifiedCost = searchHit.getContent().getISpecifiedCost();
        this.iTax = searchHit.getContent().getITax();
        this.iComprehensiveUnitPriceIncludedTax = searchHit.getContent().getIComprehensiveUnitPriceIncludedTax();
        this.iComprehensiveCombinedPricePreTax = searchHit.getContent().getIComprehensiveCombinedPricePreTax();
        this.iPrice = searchHit.getContent().getIPrice();
        this.iRemark = searchHit.getContent().getIRemark();
        this.iQuotationUnit = searchHit.getContent().getIQuotationUnit();
        this.cName = searchHit.getContent().getCName();
        this.cComprehensiveCombinedPricePreTax = searchHit.getContent().getCComprehensiveCombinedPricePreTax();
        this.cComprehensiveCombinedPriceIncludedTax = searchHit.getContent().getCComprehensiveCombinedPriceIncludedTax();
        this.tCombinedPrice = searchHit.getContent().getTCombinedPrice();
        this.tFixedLaborCost = searchHit.getContent().getTFixedLaborCost();
        this.tProvisionalEstimate = searchHit.getContent().getTProvisionalEstimate();
        this.cCombinedPrice = searchHit.getContent().getCCombinedPrice();
        this.cFixedLaborCost = searchHit.getContent().getCFixedLaborCost();
        this.cProvisionalEstimate = searchHit.getContent().getCProvisionalEstimate();
        this.iFeatureDescription = searchHit.getContent().getIFeatureDescription();
        this.iMeasurementUnit = searchHit.getContent().getIMeasurementUnit();
        this.iComprehensiveUnitPrice = searchHit.getContent().getIComprehensiveUnitPrice();
        this.iFixedLaborCost = searchHit.getContent().getIFixedLaborCost();
        this.iProvisionalEstimate = searchHit.getContent().getIProvisionalEstimate();
        this.iDescription = searchHit.getContent().getIDescription();
        this.tSummation = searchHit.getContent().getTSummation();
        this.cAmount = searchHit.getContent().getCAmount();
        this.cSettlementAmount = searchHit.getContent().getCSettlementAmount();
        this.cRemark = searchHit.getContent().getCRemark();
        this.iSettlementAmount = searchHit.getContent().getISettlementAmount();
        this.cCalculationBasis = searchHit.getContent().getCCalculationBasis();
        this.cCalculationCardinality = searchHit.getContent().getCCalculationCardinality();
        this.cCalculationRate = searchHit.getContent().getCCalculationRate();
        this.iCalculationBasis = searchHit.getContent().getICalculationBasis();
        this.iCalculationCardinality = searchHit.getContent().getICalculationCardinality();
        this.iCalculationRate = searchHit.getContent().getICalculationRate();
        this.cCode = searchHit.getContent().getCCode();
        this.iRate = searchHit.getContent().getIRate();
        this.iAdjustmentRate = searchHit.getContent().getIAdjustmentRate();
        this.iAdjustedAmount = searchHit.getContent().getIAdjustedAmount();
    }


}

