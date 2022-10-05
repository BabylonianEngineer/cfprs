package com.hf.cfprs.pojo.es;

import com.hf.cfprs.pojo.mysql.tissue.AttachedTissue;
import com.hf.cfprs.utils.SearchUtil;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.suggest.Completion;


/**
 * @Author:hanfei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "attached_tissue")
public class AttachedTissue4ES {
    @Id
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String rName;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String sName;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String uName;
    @Field(type = FieldType.Keyword)
    private String tName;
    private Double tComprehensiveCombinedPricePreTax;
    private Double tComprehensiveCombinedPriceIncludedTax;
    private String iCode;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
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
    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Completion comName;

    public AttachedTissue4ES(AttachedTissue x) {
        this.rName = x.getRName();
        this.sName = x.getSName();
        this.uName = x.getUName();
        this.tName = x.getTName();
        this.tComprehensiveCombinedPricePreTax = x.getTComprehensiveCombinedPricePreTax();
        this.tComprehensiveCombinedPriceIncludedTax = x.getTComprehensiveCombinedPriceIncludedTax();
        this.iCode = x.getICode();
        this.iName = x.getIName();
        this.iFeature = x.getIProjectFeature();
        this.iJobContent = x.getIJobContent();
        this.iMeasurementRule = x.getIMeasurementRule();
        this.iSupplyWay = x.getISupplyWay();
        this.iUnit = x.getIUnit();
        this.iQuantity = x.getIQuantity();
        this.iComprehensiveUnitPricePreTax = x.getIComprehensiveUnitPricePreTax();
        this.iLaborCost = x.getILaborCost();
        this.iMainMaterialUnitPrice = x.getIMainMaterialUnitPrice();
        this.iMainMaterialDepletion = x.getIMainMaterialDepletion();
        this.iMainMaterialCost = x.getIMainMaterialCost();
        this.iAuxiliaryMaterialCost = x.getIAuxiliaryMaterialCost();
        this.iMachiningCost = x.getIMachiningCost();
        this.iManagementCost = x.getIManagementCost();
        this.iProfit = x.getIProfit();
        this.iSpecifiedCost = x.getISpecifiedCost();
        this.iTax = x.getITax();
        this.iComprehensiveUnitPriceIncludedTax = x.getIComprehensiveUnitPriceIncludedTax();
        this.iComprehensiveCombinedPricePreTax = x.getIComprehensiveCombinedPricePreTax();
        this.iPrice = x.getIComprehensiveCombinedPriceIncludedTax();
        this.iRemark = x.getIRemark();
        this.iQuotationUnit = x.getIQuotationUnit();
        this.comName = new Completion(SearchUtil.ifNull(this.rName));

    }

    public AttachedTissue4ES(String rName, String sName, String uName, String tName, Double tComprehensiveCombinedPricePreTax, Double tComprehensiveCombinedPriceIncludedTax, String iCode, String iName, String iFeature, String iJobContent, String iMeasurementRule, String iSupplyWay, String iUnit, Double iQuantity, Double iComprehensiveUnitPricePreTax, Double iLaborCost, Double iMainMaterialUnitPrice, Double iMainMaterialDepletion, Double iMainMaterialCost, Double iAuxiliaryMaterialCost, Double iMachiningCost, Double iManagementCost, Double iProfit, Double iSpecifiedCost, Double iTax, Double iComprehensiveUnitPriceIncludedTax, Double iComprehensiveCombinedPricePreTax, Double iPrice, String iRemark, String iQuotationUnit) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tComprehensiveCombinedPricePreTax = tComprehensiveCombinedPricePreTax;
        this.tComprehensiveCombinedPriceIncludedTax = tComprehensiveCombinedPriceIncludedTax;
        this.iCode = iCode;
        this.iName = iName;
        this.iFeature = iFeature;
        this.iJobContent = iJobContent;
        this.iMeasurementRule = iMeasurementRule;
        this.iSupplyWay = iSupplyWay;
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
        this.iPrice = iPrice;
        this.iRemark = iRemark;
        this.iQuotationUnit = iQuotationUnit;
        this.comName = new Completion(SearchUtil.ifNull(rName));

    }
}
