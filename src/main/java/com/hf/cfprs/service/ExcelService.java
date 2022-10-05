package com.hf.cfprs.service;

import com.hf.cfprs.dao.mysql.*;
import com.hf.cfprs.pojo.mysql.*;
import com.hf.cfprs.dao.mysql.tissue.*;
import com.hf.cfprs.pojo.mysql.tissue.*;
import com.hf.cfprs.utils.SheetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:hanfei
 */
@Service
@Slf4j
public class ExcelService {
    @Autowired
    private CoverRepository coverRepository;
    @Autowired
    private SingleRepository singleRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private CommonTissueRepository commonTissueRepository;
    @Autowired
    private CommonAlterTissueRepository commonAlterTissueRepository;
    @Autowired
    private TotalMeasureTissueRepository totalMeasureTissueRepository;
    @Autowired
    private OtherTissueRepository otherTissueRepository;
    @Autowired
    private TaxTissueRepository taxTissueRepository;
    @Autowired
    private AttachedTissueRepository attachedTissueRepository;
    @Autowired
    private MainMaterialSheetRepository mainMaterialSheetRepository;
    @Autowired
    private MaterialSheetRepository materialSheetRepository;


    //Web parse 输出 Excel文件的所有Sheet名称
    public void parseWeb(MultipartFile file) throws Exception {
        InputStream in = file.getInputStream();
        Workbook wb = new HSSFWorkbook(in);
        List<String> sheetNames = new ArrayList<>();
        System.out.println("^--" + file.getOriginalFilename());
        System.out.println("^--Names Of Excel File's Sheets From Web:");
        //跳过封面, i从1开始
        for (int i = 1; i < wb.getNumberOfSheets(); i++) {
            String sheetName = wb.getSheetName(i);
            System.out.println(sheetName);
        }
    }

    //Web file format check;有一个sheet格式不对，则此文件不解析，前端显示上传失败;目前仅根据表-03的格式判断
    public Boolean fileFormatCheck(MultipartFile file) throws Exception {
        HSSFSheet sheetForCheck = SheetUtil.getSheetForCheck(file, "03");
        return SheetUtil.formatCheck(sheetForCheck);
    }

    //Web流--解析含有多个Sheet的Excel文件
    @Transactional
    public void parseWebMultiSheetExcel(MultipartFile file) throws Exception {
        //MultipartFile对象，Web流
        InputStream in = file.getInputStream();
        //-----
        HSSFWorkbook wb = new HSSFWorkbook(in);
        List<String> sheetNames = new ArrayList<>();
        Map<String, HSSFSheet> sheetMap = new HashMap<>();
        //跳过封面, i从1开始
        for (int i = 1; i < wb.getNumberOfSheets(); i++) {
            String sheetName = wb.getSheetName(i);
            sheetNames.add(sheetName);
            sheetMap.put(sheetName, wb.getSheet(sheetName));
        }
        wb.close();
        for (String sheetName : sheetNames) {
            try {
                boolean succPrint = true;
                switch (SheetUtil.getTypeCode(sheetName)) {
                    case "2":
                        parseCover(sheetMap.get(sheetName));
                        break;
                    case "03":
                        parseSingle(sheetMap.get(sheetName));
                        break;
                    case "04":
                        parseUnit(sheetMap.get(sheetName));
                        break;
                    case "08":
                        parseCommonTissue(sheetMap.get(sheetName));
                        break;
                    case "08改":
                        parseCommonAlterTissue(sheetMap.get(sheetName));
                        break;
                    case "11":
                        parseTotalMeasureTissue(sheetMap.get(sheetName));
                        break;
                    case "12":
                        parseOtherTissue(sheetMap.get(sheetName));
                        break;
                    case "13":
                        parseTaxTissue(sheetMap.get(sheetName));
                        break;
                    case "21":
                        parseMaterialSheet(sheetMap.get(sheetName));
                        break;
                    case "22":
                        parseMainMaterialSheet(sheetMap.get(sheetName));
                        break;
                    case "复件":
                        parseAttachedTissue(sheetMap.get(sheetName));
                        break;
                    case "Ignored":
                        SheetUtil.log("不处理信息冗余表!>>>" + sheetName);
                        succPrint = false;
                        break;
                    default:
                        SheetUtil.log("没有匹配实体!>>>" + sheetName);
                        succPrint = false;
                }
                if (succPrint) {
                    SheetUtil.log("此Sheet解析完成!>>>" + sheetName);
                }
            } catch (Exception e) {
                log.error("系统异常>>>解析此Sheet时产生:" + sheetName+"；>>>来自>>>"+file.getOriginalFilename());
                e.printStackTrace();
                throw e;
            }
        }
        //-----
        //MultipartFile对象，Web流
        SheetUtil.log("***此Excel文件解析完成!>>>" + file.getOriginalFilename());
    }

    //Path流--解析含有多个Sheet的Excel文件
    @Transactional
    public void parsePathMultiSheetExcel(File file) throws Exception {
        //File对象，文件路径
        FileInputStream in = new FileInputStream(file);
        //-----
        HSSFWorkbook wb = new HSSFWorkbook(in);
        List<String> sheetNames = new ArrayList<>();
        Map<String, HSSFSheet> sheetMap = new HashMap<>();
        //跳过封面, i从1开始
        for (int i = 1; i < wb.getNumberOfSheets(); i++) {
            String sheetName = wb.getSheetName(i);
            sheetNames.add(sheetName);
            sheetMap.put(sheetName, wb.getSheet(sheetName));
        }
        wb.close();
        for (String sheetName : sheetNames) {
            try {
                boolean succPrint = true;
                switch (SheetUtil.getTypeCode(sheetName)) {
                    case "2":
                        parseCover(sheetMap.get(sheetName));
                        break;
                    case "03":
                        parseSingle(sheetMap.get(sheetName));
                        break;
                    case "04":
                        parseUnit(sheetMap.get(sheetName));
                        break;
                    case "08":
                        parseCommonTissue(sheetMap.get(sheetName));
                        break;
                    case "08改":
                        parseCommonAlterTissue(sheetMap.get(sheetName));
                        break;
                    case "11":
                        parseTotalMeasureTissue(sheetMap.get(sheetName));
                        break;
                    case "12":
                        parseOtherTissue(sheetMap.get(sheetName));
                        break;
                    case "13":
                        parseTaxTissue(sheetMap.get(sheetName));
                        break;
                    case "21":
                        parseMaterialSheet(sheetMap.get(sheetName));
                        break;
                    case "22":
                        parseMainMaterialSheet(sheetMap.get(sheetName));
                        break;
                    case "复件":
                        parseAttachedTissue(sheetMap.get(sheetName));
                        break;
                    case "Ignored":
                        SheetUtil.log("不处理信息冗余表!>>>" + sheetName);
                        succPrint = false;
                        break;
                    default:
                        SheetUtil.log("没有匹配实体!>>>" + sheetName);
                        succPrint = false;
                }
                if (succPrint) {
                    SheetUtil.log("此Sheet解析完成!>>>" + sheetName);
                }
            } catch (Exception e) {
                log.error("系统异常>>>解析此Sheet时产生:" + sheetName+"；>>>来自>>>"+file.getName());
                e.printStackTrace();
                throw e;
            }
        }
        //-----
        SheetUtil.log(file.getName() + ">>>此Excel文件解析完成!");
    }

    // ok 2 此sheet一条数据
    public void parseCover(HSSFSheet sheet) {
        Cover cover = new Cover();
        cover.setRName(SheetUtil.cleanStr(sheet.getRow(0).getCell(5).getStringCellValue()));
        String strPrice = sheet.getRow(2).getCell(6).getStringCellValue();
        Double price = Double.parseDouble(strPrice.substring(0, strPrice.length() - 1));
        cover.setTenderControlPrice(price);
        cover.setTenderee(sheet.getRow(5).getCell(3).getStringCellValue());
        cover.setCostConsultant(sheet.getRow(5).getCell(18).getStringCellValue());
        coverRepository.save(cover);
    }

    //ok 03
    public void parseSingle(HSSFSheet sheet) {
        //(id,R名称,S名称,S合计,S暂估价,S安全文明施工费,S规费,U名称,U金额,U暂估价,U安全文明施工费,U规费)
        //(id,rName,sName,sSummation,sProvisionalEstimate,sSafetyConstructionCost,sSpecifiedCost,uName,uAmount,uProvisionalEstimate,uSafetyConstructionCost,uSpecifiedCost)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 2);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);

        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);
        Double sSummation = c2D(lastRow.getCell(3));
        Double sProvisionalEstimate = c2D(lastRow.getCell(4));
        Double sSafetyConstructionCost = c2D(lastRow.getCell(5));
        Double sSpecifiedCost = c2D(lastRow.getCell(6));

        List<Single> singleList = new ArrayList<>();
        for (int i = 4; i < rowCount - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            Single single = new Single();
            single.setRName(rName);
            single.setSName(sName);
            single.setSSummation(sSummation);
            single.setSProvisionalEstimate(sProvisionalEstimate);
            single.setSSafetyConstructionCost(sSafetyConstructionCost);
            single.setSSpecifiedCost(sSpecifiedCost);
            single.setUName(row.getCell(1).getStringCellValue());
            single.setUAmount(c2D(row.getCell(3)));
            single.setUProvisionalEstimate(c2D(row.getCell(4)));
            single.setUSafetyConstructionCost(c2D(row.getCell(5)));
            single.setUSpecifiedCost(c2D(row.getCell(6)));
            singleList.add(single);
        }
        singleRepository.saveAll(singleList);
    }

    //ok 04
    public void parseUnit(HSSFSheet sheet) {
        //(id,R名称,S名称,U名称,U金额,U暂估价,T名称,T金额,T暂估价,C名称,C金额,C暂估价)
        //(id,rName,sName,uName,uAmount,uProvisionalEstimate,tName,tAmount,tProvisionalEstimate,cName,cAmount,cProvisionalEstimate)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 2);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);
        Double uAmount = c2D(lastRow.getCell(3));
        Double uProvisionalEstimate = c2D(lastRow.getCell(4));
        String tName = null;
        Double tAmount = null;
        Double tProvisionalEstimate = null;

        List<Unit> unitList = new ArrayList<>();
        for (int i = 3; i < rowCount - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            if (checkTissueInfo(row)) {
                tName = SheetUtil.cleanStr(row.getCell(1).getStringCellValue());
                tAmount = c2D(row.getCell(3));
                tProvisionalEstimate = c2D(row.getCell(4));
                if (checkTissueInfo(sheet.getRow(i + 1)) || i + 1 == rowCount - 1) {
                    Unit u = new Unit(rName, sName, uName, uAmount, uProvisionalEstimate, tName, tAmount, tProvisionalEstimate);
                    u.setCName(null);
                    u.setCAmount(null);
                    u.setCProvisionalEstimate(null);
                    unitList.add(u);
                }
                continue;
            }
            Unit u = new Unit(rName, sName, uName, uAmount, uProvisionalEstimate, tName, tAmount, tProvisionalEstimate);
            u.setCName(row.getCell(1).getStringCellValue());
            u.setCAmount(c2D(row.getCell(3)));
            u.setCProvisionalEstimate(c2D(row.getCell(4)));
            unitList.add(u);
            unitRepository.saveAll(unitList);
        }
    }

    //ok 08
    public void parseCommonTissue(HSSFSheet sheet) {
        //(id,R名称,S名称,U名称,T名称,T合价,T定额人工费,T暂估价,C名称,C合价,C定额人工费,C暂估价,I项目编码,I项目名称,I项目特征描述,I计量单位,I工程量,I综合单价,I合价,I定额人工费,I暂估价)
        //(id,rName,sName,uName,tName,tCombinedPrice,tFixedLaborCost,tProvisionalEstimate,cName,cCombinedPrice,cFixedLaborCost,cProvisionalEstimate,iProjectCode,iProjectName,iProjectFeatureDescription,iMeasurementUnit,iQuantity,iComprehensiveUnitPrice,iCombinedPrice,iFixedLaborCost,iProvisionalEstimate)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 2);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        String tName = SheetUtil.cleanStr(sheet.getRow(0).getCell(0).getStringCellValue()).split("清")[0];
        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);
        Double tCombinedPrice = c2D(lastRow.getCell(9));
        Double tFixedLaborCost = null;
        Double tProvisionalEstimate = c2D(lastRow.getCell(10));

        //按序插入，LinkedHashMap
        Map<Integer, Integer> bte = new LinkedHashMap<>();
        int pre = 5;
        for (int i = 5; i < rowCount - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            if (checkCTCellInfoBegin(row)) {
                //保证只存最后出现的begin
                pre = i;
                continue;
            }
            if (checkCTCellInfoEnd(row)) {
                //保证只存首次出现的end
                if (bte.containsKey(pre)) {
                    continue;
                }
                bte.put(pre, i);
            }
        }
        //log(bte);

        List<CommonTissue> ctList = new ArrayList<>();
        //处理cInfo 和 iInfo
        for (Map.Entry<Integer, Integer> entry : bte.entrySet()) {
            int begin = entry.getKey();
            int end = entry.getValue();
            HSSFRow beginRow = sheet.getRow(entry.getKey());
            HSSFRow endRow = sheet.getRow(entry.getValue());
            //cName,cCombinedPrice,cFixedLaborCost,cProvisionalEstimate
            String cName = SheetUtil.cleanStr(beginRow.getCell(3).getStringCellValue());
            Double cCombinedPrice = c2D(endRow.getCell(9));
            Double cFixedLaborCost = null;
            Double cProvisionalEstimate = c2D(endRow.getCell(10));
            if (begin + 1 > end - 1) {
                CommonTissue ct = new CommonTissue(rName, sName, uName, tName, tCombinedPrice, tFixedLaborCost, tProvisionalEstimate, cName, cCombinedPrice, cFixedLaborCost, cProvisionalEstimate, null, null, null, null, null, null, null, null, null);
                ctList.add(ct);
                continue;
            }
            //iProjectCode,iProjectName,iProjectFeatureDescription,iMeasurementUnit,iQuantity,iComprehensiveUnitPrice,iCombinedPrice,iFixedLaborCost,iProvisionalEstimate
            for (int i = begin + 1; i <= end - 1; i++) {
                HSSFRow row = sheet.getRow(i);
                CommonTissue ct = new CommonTissue(rName, sName, uName, tName, tCombinedPrice, tFixedLaborCost, tProvisionalEstimate, cName, cCombinedPrice, cFixedLaborCost, cProvisionalEstimate);
                ct.setIProjectCode(row.getCell(1).getStringCellValue().trim());
                ct.setIProjectName(row.getCell(3).getStringCellValue().trim());
                ct.setIProjectFeatureDescription(SheetUtil.cleanStr(row.getCell(4).getStringCellValue()));
                ct.setIMeasurementUnit(row.getCell(5).getStringCellValue().trim());
                ct.setIQuantity(c2D(row.getCell(6)));
                ct.setIComprehensiveUnitPrice(c2D(row.getCell(8)));
                ct.setICombinedPrice(c2D(row.getCell(9)));
                ct.setIFixedLaborCost(null);
                ct.setIProvisionalEstimate(c2D(row.getCell(10)));
                ctList.add(ct);
            }
        }
        commonTissueRepository.saveAll(ctList);
    }

    //ok 08改
    public void parseCommonAlterTissue(HSSFSheet sheet) {
        //(id,R名称,S名称,U名称,T名称,T综合合价(税前),T综合合价(含税),C名称,C综合合价(税前),C综合合价(含税),I编码,I名称,I项目特征,I工作内容,I计量规则,I单位,I工程量,I综合单价(税前),I人工费,I主材单价,I主材损耗,I主材费,I辅材费,I机械费,I管理费,I利润,I规费,I税金,I综合单价(含税),I综合合价(税前),I综合合价(含税),I备注,I报价单位)
        //(id,rName,sName,uName,tName,tComprehensiveCombinedPricePreTax,tComprehensiveCombinedPriceIncludedTax,cName,cComprehensiveCombinedPricePreTax,cComprehensiveCombinedPriceIncludedTax,iCode,iName,iProjectFeature,iJobContent,iMeasurementRule,iUnit,iQuantity,iComprehensiveUnitPricePreTax,iLaborCost,iMainMaterialUnitPrice,iMainMaterialDepletion,iMainMaterialCost,iAuxiliaryMaterialCost,iMachiningCost,iManagementCost,iProfit,iSpecifiedCost,iTax,iComprehensiveUnitPriceIncludedTax,iComprehensiveCombinedPricePreTax,iComprehensiveCombinedPriceIncludedTax,iRemark,iQuotationUnit)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 2);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        String tName = SheetUtil.cleanStr(sheet.getRow(0).getCell(0).getStringCellValue()).split("清")[0];
        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);
        Double tComprehensiveCombinedPricePreTax = c2D(lastRow.getCell(23));
        Double tComprehensiveCombinedPriceIncludedTax = c2D(lastRow.getCell(24));

        //按序插入，LinkedHashMap
        Map<Integer, Integer> bte = new LinkedHashMap<>();
        int pre = 5;
        for (int i = 5; i < rowCount - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            if (checkCTCellInfoBegin(row)) {
                //保证只存最后出现的begin
                pre = i;
                continue;
            }
            if (checkCTCellInfoEnd(row)) {
                //保证只存首次出现的end
                if (bte.containsKey(pre)) {
                    continue;
                }
                bte.put(pre, i);
            }
        }
        List<CommonAlterTissue> catList = new ArrayList<>();
        //处理cInfo 和 iInfo
        for (Map.Entry<Integer, Integer> entry : bte.entrySet()) {
            int begin = entry.getKey();
            int end = entry.getValue();
            HSSFRow beginRow = sheet.getRow(entry.getKey());
            HSSFRow endRow = sheet.getRow(entry.getValue());
            //cName,cComprehensiveCombinedPricePreTax,cComprehensiveCombinedPriceIncludedTax
            String cName = SheetUtil.cleanStr(beginRow.getCell(3).getStringCellValue());
            Double cComprehensiveCombinedPricePreTax = c2D(endRow.getCell(23));
            Double cComprehensiveCombinedPriceIncludedTax = c2D(endRow.getCell(24));
            if (begin + 1 > end - 1) {
                CommonAlterTissue cat = new CommonAlterTissue(rName, sName, uName, tName, tComprehensiveCombinedPricePreTax, tComprehensiveCombinedPriceIncludedTax, cName, cComprehensiveCombinedPricePreTax, cComprehensiveCombinedPriceIncludedTax, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
                catList.add(cat);
                continue;
            }
            //iProjectCode,iProjectName,iProjectFeatureDescription,iMeasurementUnit,iQuantity,iComprehensiveUnitPrice,iCombinedPrice,iFixedLaborCost,iProvisionalEstimate
            for (int i = begin + 1; i <= end - 1; i++) {
                HSSFRow row = sheet.getRow(i);
                String iCode = row.getCell(1).getStringCellValue().trim();
                String iName = SheetUtil.cleanStr(row.getCell(3).getStringCellValue());
                String iProjectFeature = SheetUtil.cleanStr(row.getCell(4).getStringCellValue());
                String iJobContent = SheetUtil.cleanStr(row.getCell(5).getStringCellValue());
                String iMeasurementRule = SheetUtil.cleanStr(row.getCell(6).getStringCellValue());
                String iUnit = row.getCell(7).getStringCellValue().trim();
                Double iQuantity = c2D(row.getCell(8));
                Double iComprehensiveUnitPricePreTax = c2D(row.getCell(9));
                Double iLaborCost = c2D(row.getCell(10));
                Double iMainMaterialUnitPrice = c2D(row.getCell(11));
                Double iMainMaterialDepletion = c2D(row.getCell(12));
                Double iMainMaterialCost = c2D(row.getCell(13));
                Double iAuxiliaryMaterialCost = c2D(row.getCell(14));
                Double iMachiningCost = c2D(row.getCell(16));
                Double iManagementCost = c2D(row.getCell(17));
                Double iProfit = c2D(row.getCell(18));
                Double iSpecifiedCost = c2D(row.getCell(19));
                Double iTax = c2D(row.getCell(20));
                Double iComprehensiveUnitPriceIncludedTax = c2D(row.getCell(22));
                Double iComprehensiveCombinedPricePreTax = c2D(row.getCell(23));
                Double iComprehensiveCombinedPriceIncludedTax = c2D(row.getCell(24));
                String iRemark = row.getCell(25).getStringCellValue().trim();
                String iQuotationUnit = row.getCell(26).getStringCellValue().trim();
                CommonAlterTissue cat = new CommonAlterTissue(rName, sName, uName, tName, tComprehensiveCombinedPricePreTax, tComprehensiveCombinedPriceIncludedTax, cName, cComprehensiveCombinedPricePreTax, cComprehensiveCombinedPriceIncludedTax, iCode, iName, iProjectFeature, iJobContent, iMeasurementRule, iUnit, iQuantity, iComprehensiveUnitPricePreTax, iLaborCost, iMainMaterialUnitPrice, iMainMaterialDepletion, iMainMaterialCost, iAuxiliaryMaterialCost, iMachiningCost, iManagementCost, iProfit, iSpecifiedCost, iTax, iComprehensiveUnitPriceIncludedTax, iComprehensiveCombinedPricePreTax, iComprehensiveCombinedPriceIncludedTax, iRemark, iQuotationUnit);
                catList.add(cat);
            }
        }
        commonAlterTissueRepository.saveAll(catList);
    }

    //ok 11
    public void parseTotalMeasureTissue(HSSFSheet sheet) {
        //(id,R名称,S名称,U名称,T名称,T合计,C项目编码,C项目名称,C金额,I项目编码,I项目名称,I计算基础,I费率,I金额,I调整费率,I调整后金额,I备注)
        //(id,rName,sName,uName,tName,tSummation,cProjectCode,cProjectName,cAmount,iProjectCode,iProjectName,iCalculationBasis,iRate,iAmount,iAdjustmentRate,iAdjustedAmount,iRemark)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 2);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        String tName = SheetUtil.cleanStr(sheet.getRow(0).getCell(0).getStringCellValue()).split("清")[0];
        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);
        Double tSummation = c2D(lastRow.getCell(7));

        List<TotalMeasureTissue> tmtList = new ArrayList<>();
        //cProjectCode,cProjectName,cAmount
        String cProjectCode = null;
        String cProjectName = null;
        Double cAmount = null;
        for (int i = 3; i < rowCount - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            if (checkTotalTCellInfo(row)) {
                cProjectCode = row.getCell(1).getStringCellValue();
                cProjectName = SheetUtil.cleanStr(row.getCell(3).getStringCellValue());
                cAmount = c2D(row.getCell(7));
                if (checkTotalTCellInfo(sheet.getRow(i + 1)) || i + 1 == rowCount - 1) {
                    //设为空值：iProjectCode,iProjectName,iCalculationBasis,iRate,iAmount,iAdjustmentRate,iAdjustedAmount,iRemark
                    TotalMeasureTissue tmt = new TotalMeasureTissue(rName, sName, uName, tName, tSummation, cProjectCode, cProjectName, cAmount, null, null, null, null, null, null, null, null);
                    tmtList.add(tmt);
                }
                continue;
            }
            //iProjectCode,iProjectName,iCalculationBasis,iRate,iAmount,iAdjustmentRate,iAdjustedAmount,iRemark
            String iProjectCode = row.getCell(1).getStringCellValue();
            String iProjectName = SheetUtil.cleanStr(row.getCell(3).getStringCellValue());
            String iCalculationBasis = row.getCell(5).getStringCellValue();
            Double iRate = c2D(row.getCell(6));
            Double iAmount = c2D(row.getCell(7));
            Double iAdjustmentRate = c2D(row.getCell(8));
            Double iAdjustedAmount = c2D(row.getCell(10));
            String iRemark = row.getCell(11).getStringCellValue().trim();
            TotalMeasureTissue tmt = new TotalMeasureTissue(rName, sName, uName, tName, tSummation, cProjectCode, cProjectName, cAmount, iProjectCode, iProjectName, iCalculationBasis, iRate, iAmount, iAdjustmentRate, iAdjustedAmount, iRemark);
            tmtList.add(tmt);
        }
        totalMeasureTissueRepository.saveAll(tmtList);
    }

    //ok 12
    public void parseOtherTissue(HSSFSheet sheet) {
        //(id,R名称,S名称,U名称,T名称,T合计,C项目名称,C金额,C结算金额,C备注,I项目名称,I金额,I结算金额,I备注)
        //(id,rName,sName,uName,tName,tSummation,cProjectName,cAmount,cSettlementAmount,cRemark,iProjectName,iAmount,iSettlementAmount,iRemark)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 1);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        String tName = SheetUtil.cleanStr(sheet.getRow(0).getCell(0).getStringCellValue()).split("清")[0];
        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);
        Double tSummation = c2D(lastRow.getCell(2));

        List<OtherTissue> otList = new ArrayList<>();
        //cProjectName,cAmount,cSettlementAmount,cRemark,iProjectName,iAmount,iSettlementAmount,iRemark
        String cProjectName = null;
        Double cAmount = null;
        Double cSettlementAmount = null;
        String cRemark = null;
        for (int i = 3; i < rowCount - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            if (checkOtherTCellInfo(row)) {
                cProjectName = SheetUtil.cleanStr(row.getCell(1).getStringCellValue());
                cAmount = c2D(row.getCell(2));
                cSettlementAmount = c2D(row.getCell(3));
                cRemark = row.getCell(4).getStringCellValue().trim();

                if (checkOtherTCellInfo(sheet.getRow(i + 1)) || i + 1 == rowCount - 1) {
                    //设为空值：iProjectName,iAmount,iSettlementAmount,iRemark
                    OtherTissue ot = new OtherTissue(rName, sName, uName, tName, tSummation, cProjectName, cAmount, cSettlementAmount, cRemark, null, null, null, null);
                    otList.add(ot);
                }
                continue;
            }
            //iProjectName,iAmount,iSettlementAmount,iRemark
            String iProjectName = SheetUtil.cleanStr(row.getCell(1).getStringCellValue());
            Double iAmount = c2D(row.getCell(2));
            Double iSettlementAmount = c2D(row.getCell(3));
            String iRemark = row.getCell(4).getStringCellValue().trim();
            OtherTissue ot = new OtherTissue(rName, sName, uName, tName, tSummation, cProjectName, cAmount, cSettlementAmount, cRemark, iProjectName, iAmount, iSettlementAmount, iRemark);
            otList.add(ot);
        }
        otherTissueRepository.saveAll(otList);
    }

    //ok 13
    public void parseTaxTissue(HSSFSheet sheet) {
        //(id,R名称,S名称,U名称,T名称,T合计,C项目名称,C计算基础,C计算基数,C计算费率,C金额,I项目名称,I计算基础,I计算基数,I计算费率,I金额)
        //(id,rName,sName,uName,tName,tSummation,cProjectName,cCalculationBasis,cCalculationCardinality,cCalculationRate,cAmount,iProjectName,iCalculationBasis,iCalculationCardinality,iCalculationRate,iAmount)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 1);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        String tName = SheetUtil.cleanStr(sheet.getRow(0).getCell(0).getStringCellValue()).split("计")[0];
        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);
        Double tSummation = c2D(lastRow.getCell(5));

        List<TaxTissue> ttList = new ArrayList<>();
        //cProjectName,cCalculationBasis,cCalculationCardinality,cCalculationRate,cAmount,
        String cProjectName = null;
        String cCalculationBasis = null;
        Double cCalculationCardinality = null;
        Double cCalculationRate = null;
        Double cAmount = null;
        for (int i = 3; i < rowCount - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            if (checkTaxTCellInfo(row)) {
                cProjectName = SheetUtil.cleanStr(row.getCell(1).getStringCellValue());
                cCalculationBasis = row.getCell(2).getStringCellValue();
                cCalculationCardinality = c2D(row.getCell(3));
                cCalculationRate = c2D(row.getCell(4));
                cAmount = c2D(row.getCell(5));
                if (checkTaxTCellInfo(sheet.getRow(i + 1)) || i + 1 == rowCount - 1) {
                    TaxTissue t = new TaxTissue(rName, sName, uName, tName, tSummation, cProjectName, cCalculationBasis, cCalculationCardinality, cCalculationRate, cAmount, null, null, null, null, null);
                    ttList.add(t);
                }
                continue;
            }
            //iProjectName,iCalculationBasis,iCalculationCardinality,iCalculationRate,iAmount
            String iProjectName = SheetUtil.cleanStr(row.getCell(1).getStringCellValue());
            String iCalculationBasis = row.getCell(2).getStringCellValue().trim();
            Double iCalculationCardinality = c2D(row.getCell(3));
            Double iCalculationRate = c2D(row.getCell(4));
            Double iAmount = c2D(row.getCell(5));
            TaxTissue t = new TaxTissue(rName, sName, uName, tName, tSummation, cProjectName, cCalculationBasis, cCalculationCardinality, cCalculationRate, cAmount, iProjectName, iCalculationBasis, iCalculationCardinality, iCalculationRate, iAmount);
            ttList.add(t);
        }
        taxTissueRepository.saveAll(ttList);
    }

    //ok 复件
    public void parseAttachedTissue(HSSFSheet sheet) {
        //(id,R名称,S名称,U名称,T名称,T综合合价(税前),T综合合价(含税),I编码,I名称,I项目特征,I工作内容,I计量规则,I供材方式,I单位,I工程量,I综合单价(税前),I人工费,I主材单价,I主材损耗,I主材费,I辅材费,I机械费,I管理费,I利润,I规费,I税金,I综合单价(含税),I综合合价(税前),I综合合价(含税),I备注,I报价单位)
        //(id,rName,sName,uName,tName,tComprehensiveCombinedPricePreTax,tComprehensiveCombinedPriceIncludedTax,iCode,iName,iProjectFeature,iJobContent,iMeasurementRule,iSupplyWay,iUnit,iQuantity,iComprehensiveUnitPricePreTax,iLaborCost,iMainMaterialUnitPrice,iMainMaterialDepletion,iMainMaterialCost,iAuxiliaryMaterialCost,iMachiningCost,iManagementCost,iProfit,iSpecifiedCost,iTax,iComprehensiveUnitPriceIncludedTax,iComprehensiveCombinedPricePreTax,iComprehensiveCombinedPriceIncludedTax,iRemark,iQuotationUnit)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 2);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        String tName = SheetUtil.cleanStr(sheet.getRow(0).getCell(0).getStringCellValue()).split("清")[0];
        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);
        Double tComprehensiveCombinedPricePreTax = c2D(lastRow.getCell(23));
        Double tComprehensiveCombinedPriceIncludedTax = c2D(lastRow.getCell(24));

        List<AttachedTissue> atList = new ArrayList<>();
        // iCode,iName,iProjectFeature,iJobContent,iMeasurementRule,iSupplyWay,iUnit,iQuantity,iComprehensiveUnitPricePreTax,iLaborCost,iMainMaterialUnitPrice,iMainMaterialDepletion,iMainMaterialCost,iAuxiliaryMaterialCost,iMachiningCost,iManagementCost,iProfit,iSpecifiedCost,iTax,iComprehensiveUnitPriceIncludedTax,iComprehensiveCombinedPricePreTax,iComprehensiveCombinedPriceIncludedTax,iRemark,iQuotationUnit)
        for (int i = 4; i < rowCount - 1; i++) {
            HSSFRow row = sheet.getRow(i);
            String iCode = row.getCell(1).getStringCellValue().trim();
            String iName = SheetUtil.cleanStr(row.getCell(3).getStringCellValue());
            String iProjectFeature = SheetUtil.cleanStr(row.getCell(4).getStringCellValue());
            String iJobContent = SheetUtil.cleanStr(row.getCell(5).getStringCellValue());
            String iMeasurementRule = SheetUtil.cleanStr(row.getCell(6).getStringCellValue());
            String iSupplyWay = row.getCell(7).getStringCellValue().trim();
            String iUnit = row.getCell(8).getStringCellValue().trim();
            Double iQuantity = c2D(row.getCell(9));
            Double iComprehensiveUnitPricePreTax = c2D(row.getCell(10));
            Double iLaborCost = c2D(row.getCell(11));
            Double iMainMaterialUnitPrice = c2D(row.getCell(12));
            Double iMainMaterialDepletion = c2D(row.getCell(13));
            Double iMainMaterialCost = c2D(row.getCell(14));
            Double iAuxiliaryMaterialCost = c2D(row.getCell(15));
            Double iMachiningCost = c2D(row.getCell(16));
            Double iManagementCost = c2D(row.getCell(17));
            Double iProfit = c2D(row.getCell(18));
            Double iSpecifiedCost = c2D(row.getCell(19));
            Double iTax = c2D(row.getCell(20));
            Double iComprehensiveUnitPriceIncludedTax = c2D(row.getCell(22));
            Double iComprehensiveCombinedPricePreTax = c2D(row.getCell(23));
            Double iComprehensiveCombinedPriceIncludedTax = c2D(row.getCell(24));
            String iRemark = row.getCell(25).getStringCellValue().trim();
            String iQuotationUnit = row.getCell(26).getStringCellValue().trim();
            AttachedTissue at = new AttachedTissue(rName, sName, uName, tName, tComprehensiveCombinedPricePreTax, tComprehensiveCombinedPriceIncludedTax, iCode, iName, iProjectFeature, iJobContent, iMeasurementRule, iSupplyWay, iUnit, iQuantity, iComprehensiveUnitPricePreTax, iLaborCost, iMainMaterialUnitPrice, iMainMaterialDepletion, iMainMaterialCost, iAuxiliaryMaterialCost, iMachiningCost, iManagementCost, iProfit, iSpecifiedCost, iTax, iComprehensiveUnitPriceIncludedTax, iComprehensiveCombinedPricePreTax, iComprehensiveCombinedPriceIncludedTax, iRemark, iQuotationUnit);
            atList.add(at);
        }
        attachedTissueRepository.saveAll(atList);
    }

    //ok 22
    public void parseMainMaterialSheet(HSSFSheet sheet) {
    //(id,R名称,S名称,U名称,T名称,I材料名称,I描述,I单位,I单价,I备注)
    //(id,rName,sName,uName,tName,iMaterialName,iDescription,iUnit,iUnitPrice,iRemark)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 1);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        String tName = SheetUtil.cleanStr(sheet.getRow(0).getCell(0).getStringCellValue()).split("表")[0];
        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);

        List<MainMaterialSheet> mmsList = new ArrayList<>();
        //iMaterialName,iDescription,iUnit,iUnitPrice,iRemark
        for (int i = 3; i < rowCount; i++) {
            HSSFRow row = sheet.getRow(i);
            String iMaterialName = SheetUtil.cleanStr(row.getCell(1).getStringCellValue());
            String iDescription = SheetUtil.cleanStr(row.getCell(2).getStringCellValue());
            String iUnit = SheetUtil.cleanStr(row.getCell(3).getStringCellValue());
            Double iUnitPrice = c2D(row.getCell(5));
            String iRemark = row.getCell(6).getStringCellValue().trim();
            MainMaterialSheet mms = new MainMaterialSheet(rName, sName, uName, tName, iMaterialName, iDescription, iUnit, iUnitPrice, iRemark);
            mmsList.add(mms);
        }
        mainMaterialSheetRepository.saveAll(mmsList);
    }

    //ok 21
    public void parseMaterialSheet(HSSFSheet sheet) {
        //(id,R名称,S名称,U名称,T名称,I描述,I单位,I数量,I风险系数,I基准单价,I投标单价,I发承包人确认单价,I备注)
        //(id,rName,sName,uName,tName,iDescription,iUnit,iCount,iRiskFactor,iBenchmarkUnitPrice,iBidUnitPrice,iContractorToConfirmUnitPrice,iRemark)
        List<String> rsuName = SheetUtil.getRSUName(sheet, 1, 1);
        String rName = rsuName.get(0);
        String sName = rsuName.get(1);
        String uName = rsuName.get(2);

        String tName = SheetUtil.cleanStr(sheet.getRow(0).getCell(0).getStringCellValue()).split("一")[0];
        int rowCount = sheet.getPhysicalNumberOfRows();
        HSSFRow lastRow = sheet.getRow(rowCount - 1);

        List<MaterialSheet> msList = new ArrayList<>();
        //iDescription,iUnit,iCount,iRiskFactor,iBenchmarkUnitPrice,iBidUnitPrice,iContractorToConfirmUnitPrice,iRemark
        for (int i = 3; i < rowCount; i++) {
            HSSFRow row = sheet.getRow(i);
            String iDescription = row.getCell(1).getStringCellValue().trim();
            String iUnit = row.getCell(2).getStringCellValue().trim();
            Double iCount = c2D(row.getCell(3));
            Double iRiskFactor = c2D(row.getCell(4));
            Double iBenchmarkUnitPrice = c2D(row.getCell(6));
            Double iBidUnitPrice = c2D(row.getCell(7));
            Double iContractorToConfirmUnitPrice = c2D(row.getCell(8));
            String iRemark = row.getCell(9).getStringCellValue().trim();
            MaterialSheet ms = new MaterialSheet(rName, sName, uName, tName, iDescription, iUnit, iCount, iRiskFactor, iBenchmarkUnitPrice, iBidUnitPrice, iContractorToConfirmUnitPrice, iRemark);
            msList.add(ms);
        }
        materialSheetRepository.saveAll(msList);
    }


    /*
    * @以下为工具方法
    * */
    //转换Cell 为 Double
    public Double c2D(HSSFCell cell) {
        String value = cell.toString();
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(value);
        boolean flag = matcher.find();
        if (!flag) {
            //表示该项数据没有有效值
            return null;
        } else {
            return Double.parseDouble(value);
        }
    }

    //判断该行是不是关于Tissue的信息
    public boolean checkTissueInfo(HSSFRow row) {
        String value = row.getCell(0).toString().trim();
        Pattern p = Pattern.compile("^[1-9][0-9]*$");//非0开头，仅数字
        Matcher m = p.matcher(value);
        return m.find();
    }

    //判断该行是不是TotalMeasureTissue中的关于Cell的信息
    public boolean checkTotalTCellInfo(HSSFRow row) {
        return checkTissueInfo(row);
    }

    //判断该行是不是OtherTissue中的关于Cell的信息
    public boolean checkOtherTCellInfo(HSSFRow row) {
        return checkTissueInfo(row);
    }

    //判断该行是不是TaxTissue中的关于Cell的信息
    public boolean checkTaxTCellInfo(HSSFRow row) {
        return checkTissueInfo(row);
    }

    //判断该行在CommonTissue、CommonAlterTissue中是不是关于Cell信息的起始行
    public boolean checkCTCellInfoBegin(HSSFRow row) {
        boolean tmp = row.getCell(0).toString().trim().equals("");
        return tmp && !checkCTCellInfoEnd(row);
    }

    //判断该行在CommonTissue、CommonAlterTissue中是不是关于Cell信息的结束行
    public boolean checkCTCellInfoEnd(HSSFRow row) {
        String value = row.getCell(3).toString().trim();
        if (value.length() < 2) {
            return false;
        }
        return value.substring(value.length() - 2).equals("小计");
    }

}
