package com.hf.cfprs.pojo.es;

import com.hf.cfprs.pojo.mysql.tissue.TotalMeasureTissue;
import com.hf.cfprs.utils.SearchUtil;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.suggest.Completion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:hanfei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "total_measure_tissue")
public class TotalMeasureTissue4ES {
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
    private String cCode;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String cName;
    private Double cAmount;
    private String iCode;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String iName;
    private String iCalculationBasis;
    private Double iRate;
    private Double iPrice;
    private Double iAdjustmentRate;
    private Double iAdjustedAmount;
    private String iRemark;
    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Completion comName;


    public TotalMeasureTissue4ES(TotalMeasureTissue x) {
        this.rName = x.getRName();
        this.sName = x.getSName();
        this.uName = x.getUName();
        this.tName = x.getTName();
        this.tSummation = x.getTSummation();
        this.cCode = x.getCProjectCode();
        this.cName = x.getCProjectName();
        this.cAmount = x.getCAmount();
        this.iCode = x.getIProjectCode();
        this.iName = x.getIProjectName();
        this.iCalculationBasis = x.getICalculationBasis();
        this.iRate = x.getIRate();
        this.iPrice = x.getIAmount();
        this.iAdjustmentRate = x.getIAdjustmentRate();
        this.iAdjustedAmount = x.getIAdjustedAmount();
        this.iRemark = x.getIRemark();

        this.comName = new Completion(SearchUtil.ifNull(this.rName));

    }

    private List<String> removeNullName(List<String> names) {
        List<String> res = new ArrayList<>();
        for (String n : names) {
            if (n != null) {
                res.add(n);
            }
        }
        return res;
    }


    public TotalMeasureTissue4ES(String rName, String sName, String uName, String tName, Double tSummation, String cCode, String cName, Double cAmount, String iCode, String iName, String iCalculationBasis, Double iRate, Double iPrice, Double iAdjustmentRate, Double iAdjustedAmount, String iRemark) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.tSummation = tSummation;
        this.cCode = cCode;
        this.cName = cName;
        this.cAmount = cAmount;
        this.iCode = iCode;
        this.iName = iName;
        this.iCalculationBasis = iCalculationBasis;
        this.iRate = iRate;
        this.iPrice = iPrice;
        this.iAdjustmentRate = iAdjustmentRate;
        this.iAdjustedAmount = iAdjustedAmount;
        this.iRemark = iRemark;
        this.comName = new Completion(SearchUtil.ifNull(rName));


    }
}
