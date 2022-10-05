package com.hf.cfprs.service;

import com.hf.cfprs.pojo.search.ESContent;
import com.hf.cfprs.pojo.search.ESDetail;
import com.hf.cfprs.pojo.search.SuggestContent;
import com.hf.cfprs.utils.SearchUtil;
import com.hf.cfprs.utils.SheetUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.suggest.response.Suggest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:hanfei
 */
@Service
public class SearchService {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    /**
     * 关键词搜索
     *
     * @param tName   tName对应的索引名
     * @param content rName,sName,uName,cName,iName 模糊匹配
     * @param page    页码
     * @param rows    行数
     * @return
     */
    public Map<String, Object> search(String tName, String content, String filterFields, String sortOrder, int page, int rows) {

        //查询条件
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("rName", content))
                .should(QueryBuilders.matchQuery("sName", content))
                .should(QueryBuilders.matchQuery("uName", content))
                .should(QueryBuilders.matchQuery("cName", content))
                .should(QueryBuilders.matchQuery("iName", content))
                .filter(QueryBuilders.multiMatchQuery(content, filterFields.split(",")));
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("rName");
        highlightBuilder.field("sName");
        highlightBuilder.field("uName");
        highlightBuilder.field("cName");
        highlightBuilder.field("iName");

        return searchTemplate(tName, queryBuilder, highlightBuilder, sortOrder, page, rows);
    }

    /**
     * 高级检索
     *
     * @param tName
     * @param logic
     * @param dsl
     * @param sortOrder
     * @param page
     * @param rows
     * @return
     */
    public Map<String, Object> advancedSearch(String tName, String logic, String dsl, String sortOrder, int page, int rows) {
        BoolQueryBuilder advancedSearchQueryBuilder = SearchUtil.createAdvancedSearchQueryBuilder(logic, dsl);

        HighlightBuilder advancedSearchHighlightBuilder = SearchUtil.createAdvancedSearchHighlightBuilder();

        return searchTemplate(tName, advancedSearchQueryBuilder, advancedSearchHighlightBuilder, sortOrder, page, rows);

    }

    /**
     * 搜索模板，集成搜索时通用的高亮显示样式，排序、分页功能
     * 搜索结果过滤并非通用功能；在本系统业务背景中，高级检索不设置搜索结果过滤
     *
     * @param tName            用于指定用到的索引
     * @param queryBuilder     查询方式
     * @param highlightBuilder 只需设置字段名即可，其余已在本模板函数中处理
     * @param sortOrder        排序方式
     * @param page             页码
     * @param rows             每页行数
     * @return
     */
    private Map<String, Object> searchTemplate(String tName, QueryBuilder queryBuilder, HighlightBuilder highlightBuilder, String sortOrder, int page, int rows) {
        highlightBuilder.preTags(SearchUtil.preTag);
        highlightBuilder.postTags(SearchUtil.postTag);
        highlightBuilder.fragmentSize(20);
        highlightBuilder.numOfFragments(1);

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)//搜索条件
                .withHighlightBuilder(highlightBuilder)
                .withPageable(PageRequest.of(page, rows))
                .withSorts(SearchUtil.SORT_BUILDER_MAP.get(sortOrder))
                .build();
        IndexCoordinates index = IndexCoordinates.of(tName.split(","));

        SearchHits<ESContent> hits = restTemplate.search(query, ESContent.class, index);
        //查询结果
        List<SearchHit<ESContent>> searchHits = hits.getSearchHits();

        List<ESContent> dataList = new ArrayList<>();
        for (SearchHit<ESContent> hit : searchHits) {
            ESContent c = new ESContent();
            c.setId(hit.getContent().getId());
            c.setIPrice(hit.getContent().getIPrice());

            //获取当前数据高亮
            Map<String, List<String>> highlightFields = hit.getHighlightFields();
            if (highlightFields.containsKey("rName")) {
                c.setRName(highlightFields.get("rName").get(0));
            } else {
                c.setRName(hit.getContent().getRName());
            }
            if (highlightFields.containsKey("sName")) {
                c.setSName(highlightFields.get("sName").get(0));
            } else {
                c.setSName(hit.getContent().getSName());
            }
            if (highlightFields.containsKey("uName")) {
                c.setUName(highlightFields.get("uName").get(0));
            } else {
                c.setUName(hit.getContent().getUName());
            }
            if (highlightFields.containsKey("cName")) {
                c.setCName(highlightFields.get("cName").get(0));
            } else {
                c.setCName(hit.getContent().getCName());
            }
            if (highlightFields.containsKey("iName")) {
                c.setIName(highlightFields.get("iName").get(0));
            } else {
                c.setIName(hit.getContent().getIName());
            }
            dataList.add(c);
        }
        long totalHits = hits.getTotalHits();

        Map<String, Object> map = new HashMap<>();
        map.put("total", totalHits);
        map.put("dataList", dataList);

        return map;
    }

    /**
     * 根据id查询文档信息
     * 由于单价措施项目涉及两个索引，而restTemplate.get()好像不支持多索引查,故采用搜索方式
     *
     * @param tName 索引名称，已在前端处理传值
     * @param id    要查询的id
     * @return ESDetail 整合了ES中tissue层级的所有实体的字段
     */
    public Map<String, Object> getDetail(String tName, String id) {


        //按照id查询
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("_id", id));
        IndexCoordinates index = IndexCoordinates.of(tName.split(","));
        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)//搜索条件
                .build();
        SearchHits<ESDetail> hits = restTemplate.search(query, ESDetail.class, index);
        SearchHit<ESDetail> searchHit = hits.getSearchHit(0);
        ESDetail detail = new ESDetail(searchHit);

        Map<String, Object> map = new HashMap<>();
        map.put("data", detail);
        return map;

    }

    public List<Map<String,String>> getSuggest(String tName, String keyword) {
        CompletionSuggestionBuilder completionSuggestionBuilder = SuggestBuilders.completionSuggestion("comName")
                .size(5)
                .skipDuplicates(true)
                .prefix(keyword);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("name_suggest", completionSuggestionBuilder);

        Query query = new NativeSearchQueryBuilder()
                .withSuggestBuilder(suggestBuilder)
                .build();
        IndexCoordinates index = IndexCoordinates.of(tName.split(","));
        SearchHits<SuggestContent> hits = restTemplate.search(query, SuggestContent.class, index);

        List<Map<String,String>> infoList=new ArrayList<>();
        if (hits.hasSuggest()) {
            Suggest suggest = hits.getSuggest();
            Suggest.Suggestion<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> name_suggest = suggest.getSuggestion("name_suggest");
            if (name_suggest != null) {
                for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> entry : name_suggest.getEntries()) {
                    for (Suggest.Suggestion.Entry.Option option : entry.getOptions()) {
                        Map<String,String> m=new HashMap<>();
                        m.put("value",option.getText());
                        infoList.add(m);
//                        System.out.println(option.getText());
                    }
                }
            }
        }
        return infoList;
    }
}
