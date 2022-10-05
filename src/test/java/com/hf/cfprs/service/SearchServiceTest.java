package com.hf.cfprs.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author:hanfei
 */
@SpringBootTest
class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    void search() {
    }

    @Test
    public void stringParamTypeCheck(){
//        of("a","b","c");
        Double e=null;
        String s=String.valueOf(e);
        String s2=e.toString();
        System.out.println(s);
        System.out.println(s2);

    }
    public void of(String... indexNames) {
//        System.out.println(indexNames.getClass().toString());
        for(String s:indexNames){
            System.out.println(s);
        }

    }

    @Test
    void getSuggest() {
        searchService.getSuggest("total_measure_tissue","成都");
    }
}
