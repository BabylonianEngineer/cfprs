package com.hf.cfprs.pojo.es;

import com.hf.cfprs.pojo.mysql.tissue.CommonTissue;
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
@Document(indexName = "common_tissue")
public class CommonTissue4ES {
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
    @Field(type = FieldType.Text, analyzer = "keyword")
    private String tName;
    private Double tCombinedPrice;
    private Double tFixedLaborCost;
    private Double tProvisionalEstimate;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String cName;
    private Double cCombinedPrice;
    private Double cFixedLaborCost;
    private Double cProvisionalEstimate;
    @Field(type = FieldType.Keyword)
    private String iCode;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String iName;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String iFeatureDescription;
    private String iMeasurementUnit;
    private Double iQuantity;
    private Double iComprehensiveUnitPrice;
    private Double iPrice;
    private Double iFixedLaborCost;
    private Double iProvisionalEstimate;
    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Completion comName;

    public CommonTissue4ES(CommonTissue ct) {
        this.rName = ct.getRName();
        this.sName = ct.getSName();
        this.uName = ct.getUName();
        this.tName = ct.getTName();
        this.tCombinedPrice = ct.getTCombinedPrice();
        this.tFixedLaborCost = ct.getTFixedLaborCost();
        this.tProvisionalEstimate = ct.getTProvisionalEstimate();
        this.cName = ct.getCName();
        this.cCombinedPrice = ct.getCCombinedPrice();
        this.cFixedLaborCost = ct.getCFixedLaborCost();
        this.cProvisionalEstimate = ct.getCProvisionalEstimate();
        this.iCode = ct.getIProjectCode();
        this.iName = ct.getIProjectName();
        this.iFeatureDescription = ct.getIProjectFeatureDescription();
        this.iMeasurementUnit = ct.getIMeasurementUnit();
        this.iQuantity = ct.getIQuantity();
        this.iComprehensiveUnitPrice = ct.getIComprehensiveUnitPrice();
        this.iPrice = ct.getICombinedPrice();
        this.iFixedLaborCost = ct.getIFixedLaborCost();
        this.iProvisionalEstimate = ct.getIProvisionalEstimate();
        this.comName = new Completion(SearchUtil.ifNull(this.rName));


    }

    public CommonTissue4ES(String rName, String sName, String uName, String tName, Double tCombinedPrice, Double tFixedLaborCost, Double tProvisionalEstimate, String cName, Double cCombinedPrice, Double cFixedLaborCost, Double cProvisionalEstimate, String iCode, String iName, String iFeatureDescription, String iMeasurementUnit, Double iQuantity, Double iComprehensiveUnitPrice, Double iPrice, Double iFixedLaborCost, Double iProvisionalEstimate) {
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
        this.iCode = iCode;
        this.iName = iName;
        this.iFeatureDescription = iFeatureDescription;
        this.iMeasurementUnit = iMeasurementUnit;
        this.iQuantity = iQuantity;
        this.iComprehensiveUnitPrice = iComprehensiveUnitPrice;
        this.iPrice = iPrice;
        this.iFixedLaborCost = iFixedLaborCost;
        this.iProvisionalEstimate = iProvisionalEstimate;
        this.comName = new Completion(SearchUtil.ifNull(rName));

    }
}
