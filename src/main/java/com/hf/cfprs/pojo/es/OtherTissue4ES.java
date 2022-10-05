package com.hf.cfprs.pojo.es;

import com.hf.cfprs.pojo.mysql.tissue.OtherTissue;
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
@Document(indexName = "other_tissue")
public class OtherTissue4ES {
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
    private Double cAmount;
    private Double cSettlementAmount;
    private String cRemark;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String iName;
    private Double iPrice;
    private Double iSettlementAmount;
    private String iRemark;
    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Completion comName;

    public OtherTissue4ES(OtherTissue x) {
        this.rName = x.getRName();
        this.sName = x.getSName();
        this.uName = x.getUName();
        this.tName = x.getTName();
        this.tSummation = x.getTSummation();
        this.cName = x.getCProjectName();
        this.cAmount = x.getCAmount();
        this.cSettlementAmount = x.getCSettlementAmount();
        this.cRemark = x.getCRemark();
        this.iName = x.getIProjectName();
        this.iPrice = x.getIAmount();
        this.iSettlementAmount = x.getISettlementAmount();
        this.iRemark = x.getIRemark();
        this.comName = new Completion(SearchUtil.ifNull(this.rName));

    }

    public OtherTissue4ES(String rName, String sName, String uName, String tName, Double tSummation, String cName, Double cAmount, Double cSettlementAmount, String cRemark, String iName, Double iPrice, Double iSettlementAmount, String iRemark) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tSummation = tSummation;
        this.cName = cName;
        this.cAmount = cAmount;
        this.cSettlementAmount = cSettlementAmount;
        this.cRemark = cRemark;
        this.iName = iName;
        this.iPrice = iPrice;
        this.iSettlementAmount = iSettlementAmount;
        this.iRemark = iRemark;
        this.comName = new Completion(SearchUtil.ifNull(rName));

    }
}
