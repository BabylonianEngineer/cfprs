package com.hf.cfprs.pojo.es;

import com.hf.cfprs.pojo.mysql.tissue.TaxTissue;
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
@Document(indexName = "tax_tissue")
public class TaxTissue4ES {
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
    private Double tSummation;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String cName;
    private String cCalculationBasis;
    private Double cCalculationCardinality;
    private Double cCalculationRate;
    private Double cAmount;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String iName;
    private String iCalculationBasis;
    private Double iCalculationCardinality;
    private Double iCalculationRate;
    private Double iPrice;
    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Completion comName;

    public TaxTissue4ES(TaxTissue x) {
        this.rName = x.getRName();
        this.sName = x.getSName();
        this.uName = x.getUName();
        this.tName = x.getTName();
        this.tSummation = x.getTSummation();
        this.cName = x.getCProjectName();
        this.cCalculationBasis = x.getCCalculationBasis();
        this.cCalculationCardinality = x.getCCalculationCardinality();
        this.cCalculationRate = x.getCCalculationRate();
        this.cAmount = x.getCAmount();
        this.iName = x.getIProjectName();
        this.iCalculationBasis = x.getICalculationBasis();
        this.iCalculationCardinality = x.getICalculationCardinality();
        this.iCalculationRate = x.getICalculationRate();
        this.iPrice = x.getIAmount();
        this.comName = new Completion(SearchUtil.ifNull(this.rName));

    }

    public TaxTissue4ES(String rName, String sName, String uName, String tName, Double tSummation, String cName, String cCalculationBasis, Double cCalculationCardinality, Double cCalculationRate, Double cAmount, String iName, String iCalculationBasis, Double iCalculationCardinality, Double iCalculationRate, Double iPrice) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tSummation = tSummation;
        this.cName = cName;
        this.cCalculationBasis = cCalculationBasis;
        this.cCalculationCardinality = cCalculationCardinality;
        this.cCalculationRate = cCalculationRate;
        this.cAmount = cAmount;
        this.iName = iName;
        this.iCalculationBasis = iCalculationBasis;
        this.iCalculationCardinality = iCalculationCardinality;
        this.iCalculationRate = iCalculationRate;
        this.iPrice = iPrice;
        this.comName = new Completion(SearchUtil.ifNull(rName));

    }
}
