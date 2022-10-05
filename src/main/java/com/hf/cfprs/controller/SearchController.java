package com.hf.cfprs.controller;

import com.hf.cfprs.pojo.es.CommonTissue4ES;
import com.hf.cfprs.pojo.es.TreeNode4ES;
import com.hf.cfprs.pojo.search.ESContent;
import com.hf.cfprs.service.ESService;
import com.hf.cfprs.service.SearchService;
import com.hf.cfprs.utils.SheetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author:hanfei
 */
@RestController
@RequestMapping("/project")
public class SearchController {

    @Autowired
    private SearchService searchService;
    @Autowired
    private ESService esService;

    @GetMapping("/search")
    public Map<String, Object> search(String tName, String content, String filterFields, String sortOrder, Integer page, Integer rows) {
        Long beginTime = System.currentTimeMillis();
        Map<String, Object> search = searchService.search(tName, content, filterFields, sortOrder, page, rows);
        Long searchUseTime = System.currentTimeMillis() - beginTime;
        SheetUtil.logTime("searchUseTime:"+searchUseTime);
        return search;
    }

    @GetMapping("/advanced")
    public Map<String, Object> advanced(String tName, String logic, String dsl, String sortOrder, Integer page, Integer rows) {
        Long beginTime = System.currentTimeMillis();
        Map<String, Object> map = searchService.advancedSearch(tName, logic, dsl, sortOrder, page, rows);
        Long advancedSearchUseTime = System.currentTimeMillis() - beginTime;
        SheetUtil.logTime("advancedSearchUseTime:"+advancedSearchUseTime);
        return map;
    }

    @GetMapping("/detail")
    public Map<String, Object> detail(String tName, String id) {
        Long beginTime = System.currentTimeMillis();
        Map<String, Object> detail = searchService.getDetail(tName, id);
        Long detailUseTime = System.currentTimeMillis() - beginTime;
        SheetUtil.logTime("detailUseTime:"+detailUseTime);
        return detail;
    }

    @GetMapping("/suggest")
    public List<Map<String, String>> suggest(String tName, String content) {
        return searchService.getSuggest(tName, content);
    }

    @GetMapping("/tree")
    public TreeNode4ES disTree(String rName) {
        Long beginTime = System.currentTimeMillis();
        TreeNode4ES treeNode4ES = esService.buildTree(rName);
        Long treeUseTime = System.currentTimeMillis() - beginTime;
        SheetUtil.logTime("treeUseTime:"+treeUseTime);
        return treeNode4ES;

    }

    @GetMapping("/rTable")
    public Map<String, List<?>> disRTable(String rName) {
        Long beginTime = System.currentTimeMillis();
        Map<String, List<?>> stringListMap = esService.disRTable(rName);
        Long rTableUseTime = System.currentTimeMillis() - beginTime;
        SheetUtil.logTime("rTableUseTime:"+rTableUseTime);
        return stringListMap;
    }

    @GetMapping("/sTable")
    public Map<String, List<?>> disSTable(String rName, String sName) {
        Long beginTime = System.currentTimeMillis();
        Map<String, List<?>> stringListMap = esService.disSTable(rName, sName);
        Long sTableUseTime = System.currentTimeMillis() - beginTime;
        SheetUtil.logTime("sTableUseTime:"+sTableUseTime);
        return stringListMap;
    }

    @GetMapping("/uTable")
    public Map<String, List<?>> disUTable(String rName, String sName, String uName) {
        Long beginTime = System.currentTimeMillis();
        Map<String, List<?>> stringListMap = esService.disUTable(rName, sName, uName);
        Long uTableUseTime = System.currentTimeMillis() - beginTime;
        SheetUtil.logTime("uTableUseTime:"+uTableUseTime);
        return stringListMap;
    }

//    @GetMapping("/tTable")
//    public Map<String, List<?>> disTTable(String rName, String sName, String uName, String tName) {
//        return esService.disTTable(rName, sName, uName, tName);
//    }


}
