package com.hf.cfprs.service;

import com.hf.cfprs.dao.es.*;
import com.hf.cfprs.dao.mysql.CoverRepository;
import com.hf.cfprs.dao.mysql.MainMaterialSheetRepository;
import com.hf.cfprs.dao.mysql.SingleRepository;
import com.hf.cfprs.dao.mysql.UnitRepository;
import com.hf.cfprs.dao.mysql.tissue.*;
import com.hf.cfprs.pojo.es.*;
import com.hf.cfprs.pojo.mysql.Cover;
import com.hf.cfprs.pojo.mysql.MainMaterialSheet;
import com.hf.cfprs.pojo.mysql.Single;
import com.hf.cfprs.pojo.mysql.Unit;
import com.hf.cfprs.pojo.mysql.tissue.*;
import com.hf.cfprs.utils.SheetUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.suggest.Completion;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author:hanfei
 */
@Service
public class ESService {
    //mysql
    @Autowired
    private CommonTissueRepository commonTissueRepository;
    @Autowired
    private CommonAlterTissueRepository commonAlterTissueRepository;
    @Autowired
    private TotalMeasureTissueRepository totalMeasureTissueRepository;
    @Autowired
    private AttachedTissueRepository attachedTissueRepository;
    @Autowired
    private OtherTissueRepository otherTissueRepository;
    @Autowired
    private TaxTissueRepository taxTissueRepository;
    @Autowired
    private MainMaterialSheetRepository mainMaterialSheetRepository;
    //
    @Autowired
    private CoverRepository coverRepository;
    @Autowired
    private SingleRepository singleRepository;
    @Autowired
    private UnitRepository unitRepository;

    //es
    @Autowired
    private CommonTissue4ESRepository commonTissue4ESRepository;
    @Autowired
    private CommonAlterTissue4ESRepository commonAlterTissue4ESRepository;
    @Autowired
    private TotalMeasureTissue4ESRepository totalMeasureTissue4ESRepository;
    @Autowired
    private AttachedTissue4ESRepository attachedTissue4ESRepository;
    @Autowired
    private OtherTissue4ESRepository otherTissue4ESRepository;
    @Autowired
    private TaxTissue4ESRepository taxTissue4ESRepository;
    @Autowired
    private MainMaterialSheet4ESRepository mainMaterialSheet4ESRepository;

    public void m2eCommonTissue() {
        List<CommonTissue> all = commonTissueRepository.findAll();
        List<CommonTissue4ES> res = new ArrayList<>();
        for (CommonTissue x : all) {
            CommonTissue4ES r = new CommonTissue4ES(x);
            res.add(r);
        }
        System.out.println("数据量：" + res.size());
        commonTissue4ESRepository.saveAll(res);
        System.out.println("成功写入！");
    }

    public List<CommonTissue4ES> searchAll() {
        Iterable<CommonTissue4ES> all = commonTissue4ESRepository.findAll();
        List<CommonTissue4ES> res = new ArrayList<>();
        all.forEach(res::add);
        return res;

    }

    public void m2eCommonAlterTissue() {
        List<CommonAlterTissue> all = commonAlterTissueRepository.findAll();
        List<CommonAlterTissue4ES> res = new ArrayList<>();
        for (CommonAlterTissue x : all) {
            CommonAlterTissue4ES r = new CommonAlterTissue4ES(x);
            res.add(r);
        }
        commonAlterTissue4ESRepository.saveAll(res);
        System.out.println("成功写入" + res.size() + "条数据！");

    }

    public void m2eTotalMeasureTissue() {
        List<TotalMeasureTissue> all = totalMeasureTissueRepository.findAll();
        List<TotalMeasureTissue4ES> res = new ArrayList<>();
        for (TotalMeasureTissue x : all) {
            TotalMeasureTissue4ES r = new TotalMeasureTissue4ES(x);
            res.add(r);
        }
        totalMeasureTissue4ESRepository.saveAll(res);
        System.out.println("成功写入" + res.size() + "条数据！");

    }

    public void m2eAttachedTissue() {
        List<AttachedTissue> all = attachedTissueRepository.findAll();
        List<AttachedTissue4ES> res = new ArrayList<>();
        for (AttachedTissue x : all) {
            AttachedTissue4ES r = new AttachedTissue4ES(x);
            res.add(r);
        }
        attachedTissue4ESRepository.saveAll(res);
        System.out.println("成功写入" + res.size() + "条数据！");

    }

    public void m2eOtherTissue() {
        List<OtherTissue> all = otherTissueRepository.findAll();
        List<OtherTissue4ES> res = new ArrayList<>();
        for (OtherTissue x : all) {
            OtherTissue4ES r = new OtherTissue4ES(x);
            res.add(r);
        }
        otherTissue4ESRepository.saveAll(res);
        System.out.println("成功写入" + res.size() + "条数据！");

    }

    public void m2eTaxTissue() {
        List<TaxTissue> all = taxTissueRepository.findAll();
        List<TaxTissue4ES> res = new ArrayList<>();
        for (TaxTissue x : all) {
            TaxTissue4ES r = new TaxTissue4ES(x);
            res.add(r);
        }
        taxTissue4ESRepository.saveAll(res);
        System.out.println("成功写入" + res.size() + "条数据！");

    }

    public void m2eMainMaterialSheet() {
        List<MainMaterialSheet> all = mainMaterialSheetRepository.findAll();
        List<MainMaterialSheet4ES> res = new ArrayList<>();
        for (MainMaterialSheet x : all) {
            MainMaterialSheet4ES r = new MainMaterialSheet4ES(x);
            res.add(r);
        }
        mainMaterialSheet4ESRepository.saveAll(res);
        System.out.println("成功写入" + res.size() + "条数据！");
    }

    public TreeNode4ES buildTree(String rName) {
//        String rName = "宜宾市新能源汽车及零部件产业园基础设施一期项目（固废站+门卫室+污水处理站+PACK车间+试验中心）";
        List<Cover> coverList = coverRepository.findByrName(rName);
        if (coverList.size() == 0) {
            return null;
        }
        TreeNode4ES root = new TreeNode4ES(rName, coverList.get(0).getTenderControlPrice());
        List<TreeNode4ES> sList = new ArrayList<>();
        List<Single> singleList = singleRepository.findByrName(rName);
        Map<String, List<TreeNode4ES>> sMul = new HashMap<>();
        for (Single single : singleList) {
            sMul.computeIfAbsent(single.getSName(), k -> new ArrayList<>()).add(new TreeNode4ES(single.getUName(), single.getUAmount()));
        }
        Set<String> sSet = new HashSet<>();
        for (Single single : singleList) {
            if (sSet.contains(single.getSName())) {
                continue;
            }
            sSet.add(single.getSName());
            TreeNode4ES s = new TreeNode4ES(single.getSName(), single.getSSummation());
            s.setChildren(sMul.get(single.getSName()));
            sList.add(s);
        }
        root.setChildren(sList);
        return root;
    }

    public Map<String, List<?>> disRTable(String rName) {
        Map<String, List<?>> map = new HashMap<>();
        List<Single> singleList = singleRepository.findByrName(rName);
        List<String> xList = new ArrayList<>();
        List<Double> vList = new ArrayList<>();
        Set<String> sSet = new HashSet<>();
        for (Single single : singleList) {
            if (sSet.contains(single.getSName())) {
                continue;
            }
            sSet.add(single.getSName());

            xList.add(single.getSName());
            vList.add(single.getSSummation());
        }
        map.put("xList", xList);
        map.put("vList", vList);
        return map;
    }

    public Map<String, List<?>> disSTable(String rName, String sName) {
        Map<String, List<?>> map = new HashMap<>();
        List<Single> singleList = singleRepository.findByrNameAndsName(rName, sName);
        List<String> xList = new ArrayList<>();
        List<Double> vList = new ArrayList<>();
        Set<String> sSet = new HashSet<>();
        for (Single single : singleList) {
            System.out.println(single);
            xList.add(single.getUName());
            vList.add(single.getUAmount());
        }
        map.put("xList", xList);
        map.put("vList", vList);
        return map;
    }

    public Map<String, List<?>> disUTable(String rName, String sName, String uName) {
        Map<String, List<?>> map = new HashMap<>();
        List<Unit> unitList = unitRepository.findByrNameAndsNameAnduName(rName, sName, uName);
        List<String> xList = new ArrayList<>();
        List<Double> vList = new ArrayList<>();
        Set<String> tSet = new HashSet<>();
        for (Unit unit : unitList) {
            if (tSet.contains(unit.getTName())) {
                continue;
            }
            tSet.add(unit.getTName());
            xList.add(unit.getTName());
            vList.add(unit.getTAmount());
        }
        map.put("xList", xList);
        map.put("vList", vList);
        return map;

    }

    public Map<String, List<?>> disTTable(String rName, String sName, String uName, String tName) {
//        List<Unit> unitList = unitRepository.findByrsuT("北川羌族自治县传染病专科医院集中隔离留观中心项目", "集中隔离留观中心", "建筑与装饰工程", "分部%");

        Map<String, List<?>> map = new HashMap<>();
        List<String> xList = new ArrayList<>();
        List<Double> vList = new ArrayList<>();
        List<Unit> unitList = unitRepository.findByrsuT(rName, sName, uName, tName);
        for (Unit unit : unitList) {
            xList.add(unit.getCName());
            vList.add(unit.getCAmount());
        }
        map.put("xList", xList);
        map.put("vList", vList);
        return map;


    }

    public void initMapping() {
        attachedTissue4ESRepository.save(new AttachedTissue4ES("rName", "sName", "uName", "tName", 0.0, 0.0, "iCode", "iName", "iFeature", "iJobContent", "iMeasurementRule", "iSupplyWay", "iUnit", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "iRemark", "iQuotationUnit"));
        commonAlterTissue4ESRepository.save(new CommonAlterTissue4ES("rName", "sName", "uName", "tName", 0.0, 0.0, "cName", 0.0, 0.0, "iCode", "iName", "iFeature", "iJobContent", "iMeasurementRule", "iUnit", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "iRemark", "iQuotationUnit"));
        commonTissue4ESRepository.save(new CommonTissue4ES("rName", "sName", "uName", "tName", 0.0, 0.0, 0.0, "cName", 0.0, 0.0, 0.0, "iCode", "iName", "iFeatureDescription", "iMeasurementUnit", 0.0, 0.0, 0.0, 0.0, 0.0));
        mainMaterialSheet4ESRepository.save(new MainMaterialSheet4ES("rName","sName","uName","tName","iName","iDescription","iUnit",0.0,"iRemark"));
        otherTissue4ESRepository.save(new OtherTissue4ES("rName","sName","uName","tName",0.0,"cName",0.0,0.0,"cRemark","iName",0.0,0.0,"iRemark"));
        taxTissue4ESRepository.save(new TaxTissue4ES("rName","sName","uName","tName",0.0,"cName","cCalculationBasis",0.0,0.0,0.0,"iName","iCalculationBasis",0.0,0.0,0.0));
        totalMeasureTissue4ESRepository.save(new TotalMeasureTissue4ES("rName","sName","uName","tName",0.0,"cCode","cName",0.0,"iCode","iName","iCalculationBasis",0.0,0.0,0.0,0.0,"iRemark"));
    }
}
