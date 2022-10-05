package com.hf.cfprs.pojo.es;

import com.hf.cfprs.pojo.mysql.tissue.CommonAlterTissue;
import com.hf.cfprs.utils.SearchUtil;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
@Document(indexName = "common_alter_tissue")
public class CommonAlterTissue4ES {

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
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String cName;
    private Double cComprehensiveCombinedPricePreTax;
    private Double cComprehensiveCombinedPriceIncludedTax;
    private String iCode;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String iName;
    private String iFeature;
    private String iJobContent;
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
    private Double iPrice;
    private String iRemark;
    private String iQuotationUnit;
    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Completion comName;

    public CommonAlterTissue4ES(CommonAlterTissue cat) {
        this.rName = cat.getRName();
        this.sName = cat.getSName();
        this.uName = cat.getUName();
        this.tName = cat.getTName();
        this.tComprehensiveCombinedPricePreTax = cat.getTComprehensiveCombinedPricePreTax();
        this.tComprehensiveCombinedPriceIncludedTax = cat.getTComprehensiveCombinedPriceIncludedTax();
        this.cName = cat.getCName();
        this.cComprehensiveCombinedPricePreTax = cat.getCComprehensiveCombinedPricePreTax();
        this.cComprehensiveCombinedPriceIncludedTax = cat.getCComprehensiveCombinedPriceIncludedTax();
        this.iCode = cat.getICode();
        this.iName = cat.getIName();
        this.iFeature = cat.getIProjectFeature();
        this.iJobContent = cat.getIJobContent();
        this.iMeasurementRule = cat.getIMeasurementRule();
        this.iUnit = cat.getIUnit();
        this.iQuantity = cat.getIQuantity();
        this.iComprehensiveUnitPricePreTax = cat.getIComprehensiveUnitPricePreTax();
        this.iLaborCost = cat.getILaborCost();
        this.iMainMaterialUnitPrice = cat.getIMainMaterialUnitPrice();
        this.iMainMaterialDepletion = cat.getIMainMaterialDepletion();
        this.iMainMaterialCost = cat.getIMainMaterialCost();
        this.iAuxiliaryMaterialCost = cat.getIAuxiliaryMaterialCost();
        this.iMachiningCost = cat.getIMachiningCost();
        this.iManagementCost = cat.getIManagementCost();
        this.iProfit = cat.getIProfit();
        this.iSpecifiedCost = cat.getISpecifiedCost();
        this.iTax = cat.getITax();
        this.iComprehensiveUnitPriceIncludedTax = cat.getIComprehensiveUnitPriceIncludedTax();
        this.iComprehensiveCombinedPricePreTax = cat.getIComprehensiveCombinedPricePreTax();
        this.iPrice = cat.getIComprehensiveCombinedPriceIncludedTax();
        this.iRemark = cat.getIRemark();
        this.iQuotationUnit = cat.getIQuotationUnit();
        this.comName = new Completion(SearchUtil.ifNull(this.rName));

    }

    public CommonAlterTissue4ES(String rName, String sName, String uName, String tName, Double tComprehensiveCombinedPricePreTax, Double tComprehensiveCombinedPriceIncludedTax, String cName, Double cComprehensiveCombinedPricePreTax, Double cComprehensiveCombinedPriceIncludedTax, String iCode, String iName, String iFeature, String iJobContent, String iMeasurementRule, String iUnit, Double iQuantity, Double iComprehensiveUnitPricePreTax, Double iLaborCost, Double iMainMaterialUnitPrice, Double iMainMaterialDepletion, Double iMainMaterialCost, Double iAuxiliaryMaterialCost, Double iMachiningCost, Double iManagementCost, Double iProfit, Double iSpecifiedCost, Double iTax, Double iComprehensiveUnitPriceIncludedTax, Double iComprehensiveCombinedPricePreTax, Double iPrice, String iRemark, String iQuotationUnit) {
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
        this.iFeature = iFeature;
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
        this.iPrice = iPrice;
        this.iRemark = iRemark;
        this.iQuotationUnit = iQuotationUnit;
        this.comName = new Completion(SearchUtil.ifNull(rName));

    }
}
