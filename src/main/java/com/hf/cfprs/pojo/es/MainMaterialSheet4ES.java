package com.hf.cfprs.pojo.es;

import com.hf.cfprs.pojo.mysql.MainMaterialSheet;
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
@Document(indexName = "main_material_sheet")
public class MainMaterialSheet4ES {
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
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String iName;
    private String iDescription;
    private String iUnit;
    private Double iPrice;
    private String iRemark;
    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Completion comName;

    public MainMaterialSheet4ES(MainMaterialSheet x) {
        this.rName = x.getRName();
        this.sName = x.getSName();
        this.uName = x.getUName();
        this.tName = x.getTName();
        this.iName = x.getIMaterialName();
        this.iDescription = x.getIDescription();
        this.iUnit = x.getIUnit();
        this.iPrice = x.getIUnitPrice();
        this.iRemark = x.getIRemark();
        this.comName = new Completion(SearchUtil.ifNull(this.rName));



    }

    public MainMaterialSheet4ES(String rName, String sName, String uName, String tName, String iName, String iDescription, String iUnit, Double iPrice, String iRemark) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.tName = tName;
        this.iName = iName;
        this.iDescription = iDescription;
        this.iUnit = iUnit;
        this.iPrice = iPrice;
        this.iRemark = iRemark;
        this.comName = new Completion(SearchUtil.ifNull(rName));

    }
}
