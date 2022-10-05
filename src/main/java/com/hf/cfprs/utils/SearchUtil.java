package com.hf.cfprs.utils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:hanfei
 */
public class SearchUtil {

    public static String[] sequenceArr = {"rName", "sName", "uName", "cName", "iName"};
    public static String preTag = "<span style='background-color:#0fee8d'>";
    public static String postTag = "</span>";
    public final static Map<String, SortBuilder> SORT_BUILDER_MAP = new HashMap<String, SortBuilder>() {
        {
            put("null", SortBuilders.fieldSort("_score").order(SortOrder.DESC));
            put("ascending", SortBuilders.fieldSort("iPrice").order(SortOrder.ASC));
            put("descending", SortBuilders.fieldSort("iPrice").order(SortOrder.DESC));
        }
    };


    /**
     * 生成高级检索的QueryBuilder
     *
     * @param logic 逻辑关系词,多个,以‘#’分隔
     * @param dsl   dsl,多个,以‘#’分隔
     */
    public static BoolQueryBuilder createAdvancedSearchQueryBuilder(String logic, String dsl) {
        BoolQueryBuilder bool = new BoolQueryBuilder();
        String[] logicArr = logic.split("#");
        String[] dslArr = dsl.split("#");

        for (int i = 0; i < logicArr.length; i++) {
            if (logicArr[i].equals("must")) {
                bool.must(new WrapperQueryBuilder(dslArr[i]));
            }
            if (logicArr[i].equals("should")) {
                bool.should(new WrapperQueryBuilder(dslArr[i]));
            }
            if (logicArr[i].equals("must_not")) {
                bool.mustNot(new WrapperQueryBuilder(dslArr[i]));
            }
        }

//        Map<String, List<WrapperQueryBuilder>> map = new HashMap<>();
//        for (int i = 0; i < logicArr.length; i++) {
//            map.computeIfAbsent(logicArr[i], k -> new ArrayList<>()).add(new WrapperQueryBuilder(dslArr[i]));
//        }
//        if (map.containsKey("must")) {
//            for (WrapperQueryBuilder w : map.get("must")) {
//                bool.must(w);
//            }
//        }
//        if (map.containsKey("should")) {
//            for (WrapperQueryBuilder w : map.get("should")) {
//                bool.should(w);
//            }
//        }
//        if (map.containsKey("must_not")) {
//            for (WrapperQueryBuilder w : map.get("must_not")) {
//                bool.mustNot(w);
//            }
//        }

        return bool;
    }

    /**
     * 生成高级检索的HighlightBuilder
     *
     * @return
     */
    public static HighlightBuilder createAdvancedSearchHighlightBuilder() {
        HighlightBuilder highlightBuilder = new HighlightBuilder();

        for (String aSequenceArr : sequenceArr) {
            highlightBuilder.field(aSequenceArr);
        }
        return highlightBuilder;
    }

    /**
     * 为Completion字段的构造函数生成参数
     * @param completion
     * @return
     */
    public static String[] ifNull(String completion){
        String defaultName="无";
        if(completion!=null){
            return completion.split(",");
        }
        return defaultName.split(",");
    }
}
