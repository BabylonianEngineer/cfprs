package com.hf.cfprs.pojo.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author:hanfei
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Unit {
//    表-04
//    (id,R名称,S名称,U名称,U金额,U暂估价,T名称,T金额,T暂估价,C名称,C金额,C暂估价)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rName;
    private String sName;
    private String uName;
    private Double uAmount;
    private Double uProvisionalEstimate;
    private String tName;
    private Double tAmount;
    private Double tProvisionalEstimate;
    private String cName;
    private Double cAmount;
    private Double cProvisionalEstimate;

    public Unit(String rName, String sName, String uName, Double uAmount, Double uProvisionalEstimate, String tName, Double tAmount, Double tProvisionalEstimate) {
        this.rName = rName;
        this.sName = sName;
        this.uName = uName;
        this.uAmount = uAmount;
        this.uProvisionalEstimate = uProvisionalEstimate;
        this.tName = tName;
        this.tAmount = tAmount;
        this.tProvisionalEstimate = tProvisionalEstimate;
    }


}
