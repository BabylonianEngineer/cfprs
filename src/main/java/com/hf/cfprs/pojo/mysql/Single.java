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
public class Single {
//表-03
// (id,R名称,S名称,S合计,S暂估价,S安全文明施工费,S规费,U名称,U金额,U暂估价,U安全文明施工费,U规费)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rName;
    private String sName;
    private Double sSummation;
    private Double sProvisionalEstimate;
    private Double sSafetyConstructionCost;
    private Double sSpecifiedCost;
    private String uName;
    private Double uAmount;
    private Double uProvisionalEstimate;
    private Double uSafetyConstructionCost;
    private Double uSpecifiedCost;

}
