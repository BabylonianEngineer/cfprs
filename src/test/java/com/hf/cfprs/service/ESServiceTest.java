package com.hf.cfprs.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @Author:hanfei
 */
@SpringBootTest
class ESServiceTest {
    @Autowired
    private ESService esService;

    @Autowired
    private ElasticsearchRestTemplate template;


    @Test
    void printTemplate() {
        System.out.println(template);
    }


    @Test
    void buildTree() {
        esService.buildTree("test");
    }

    @Test
    void disSTable() {
        esService.disSTable("北川羌族自治县传染病专科医院集中隔离留观中心项目", "集中隔离留观中心");
    }

    @Test
    void disTTable() {
        esService.disTTable("", "", "", "");
    }

    @Test
        //会超时
    void m2eAll() {
        esService.m2eAttachedTissue();
        esService.m2eCommonAlterTissue();
        esService.m2eCommonTissue();
        esService.m2eMainMaterialSheet();
        esService.m2eOtherTissue();
        esService.m2eTaxTissue();
        esService.m2eTotalMeasureTissue();
    }

    @Test
    void m2eAttachedTissue() {
        esService.m2eAttachedTissue();
    }

    @Test
    void m2eCommonAlterTissue() {
        esService.m2eCommonAlterTissue();
    }

    @Test
    void m2eCommonTissue() {
        esService.m2eCommonTissue();
    }

    @Test
    void m2eMainMaterialSheet() {
        esService.m2eMainMaterialSheet();
    }

    @Test
    void m2eOtherTissue() {
        esService.m2eOtherTissue();
    }

    @Test
    void m2eTaxTissue() {
        esService.m2eTaxTissue();
    }

    @Test
    void m2eTotalMeasureTissue() {
        esService.m2eTotalMeasureTissue();
    }

    @Test
    void initMapping() {
        esService.initMapping();
    }
}
