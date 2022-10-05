package com.hf.cfprs.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:hanfei
 */
@Slf4j
public class SheetUtil {
    public static String splitNameRegex = "[\\\\]";
    private static final String ft03 = "单位工程名称,金额（元）,其中:(元),暂估价,安全文明施工费,规费";
    private static Map<String, String> t2f = new HashMap<>();


    public static boolean formatCheck(HSSFSheet sheet) {
        initT2f();
        String nowType = getTypeCode(sheet.getSheetName());
        String standardFormat = t2f.get(nowType);
        String nowFormat = getNowFormat(sheet, nowType);
        return nowFormat.equals(standardFormat);
    }

    private static void initT2f() {
        t2f.put("03", ft03);
    }

    //获取当前sheet的格式信息
    private static String getNowFormat(HSSFSheet sheet, String nowType) {
        String res = "";
        switch (nowType) {
            case "03":
                res = getFtSingle(sheet);
                break;
            default:
                log(sheet.getSheetName() + ">>>无法匹配格式处理函数!");//应该到不了
        }
        return res;
    }

    //ft03"单位工程名称,金额（元）,其中:(元),暂估价,安全文明施工费,规费"
    private static String getFtSingle(HSSFSheet sheet) {
        StringBuilder sb = new StringBuilder();
        String s1 = sheet.getRow(2).getCell(1).getStringCellValue();
        String s2 = sheet.getRow(2).getCell(3).getStringCellValue();
        String s3 = sheet.getRow(2).getCell(4).getStringCellValue();
        String s4 = sheet.getRow(3).getCell(4).getStringCellValue();
        String s5 = sheet.getRow(3).getCell(5).getStringCellValue();
        String s6 = sheet.getRow(3).getCell(6).getStringCellValue();
        sb.append(s1).append(",")
                .append(s2).append(",")
                .append(s3).append(",")
                .append(s4).append(",")
                .append(s5).append(",")
                .append(s6);
        return cleanStr(sb.toString());
    }

    public static String cleanStr(String str) {
        return str.replaceAll("\\s", "");
    }

    public static void log(Object o) {
        log.info("^{}", o);
    }
    public static void logTime(Object o) {
        log.info("$$${}", o);
    }

    //获取表对应结构的类型
    public static String getTypeCode(String name) {
        String cas1 = "表-02";
        String cas2 = "扉-2-1";
        if (name.contains(cas1) || name.contains(cas2)) {
            return "Ignored";
        }
        String cas3 = "表-22";
        if (name.contains(cas3)) {
            return "22";
        }
        //取表的编号
        Pattern pattern = Pattern.compile("(\\(\\S-)(\\d+)(\\))");
        Matcher matcher = pattern.matcher(name);
        boolean flag = matcher.find();

        Pattern pAlter = Pattern.compile("(\\(\\S-)(\\d+)(\\))(--)(\\S)");
        Matcher mAlter = pAlter.matcher(name);
        boolean fAlter = mAlter.find();

        if (fAlter) {
            return mAlter.group(2) + mAlter.group(5);// 08改
        }
        if (flag) {
            return matcher.group(2);
        } else {
            //复件
            return name.substring(0, 2);
        }

    }

    //Web流，获取excel文件中的某一类型的某一Sheet,以方便用于格式判断
    public static HSSFSheet getSheetForCheck(MultipartFile file, String typeCode) throws Exception {
        InputStream in = file.getInputStream();
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

        HSSFSheet res = null;
        for (String sheetName : sheetNames) {
            if (SheetUtil.getTypeCode(sheetName).equals(typeCode)) {
                res = sheetMap.get(sheetName);
                break;
            }
        }
        return res;
    }

    //Path流，获取excel文件中的某一类型的某一Sheet,以方便用于测试
    public static HSSFSheet getSheetForTest(String path, String typeCode) throws Exception {
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
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

        HSSFSheet res = null;
        for (String sheetName : sheetNames) {
            if (SheetUtil.getTypeCode(sheetName).equals(typeCode)) {
                res = sheetMap.get(sheetName);
                break;
            }
        }
        return res;
    }

    //输出Sheet内容
    public static void displaySheetContent(HSSFSheet sheet) {
        System.out.println("结构Sheet内容如下：");
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            HSSFRow row = sheet.getRow(i);
            System.out.print(String.format("%4s", i + ">"));
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                HSSFCell cell = row.getCell(j);
                System.out.print("[" + String.format("%2s", j) + ">");
                System.out.print(cell);
                System.out.print("]");

            }
            System.out.println();
        }
    }

    //解析获取Sheet中的rName,sName,uName
    public static List<String> getRSUName(HSSFSheet sheet, int rowIndex, int cellNum) {
        List<String> res = new ArrayList<>();
        String[] split = SheetUtil.cleanStr(sheet.getRow(rowIndex).getCell(cellNum).getStringCellValue()).split("【");
        String[] rsSplit = split[0].split(SheetUtil.splitNameRegex);
        String rName = null;
        String sName = rsSplit[0];
        if (rsSplit.length >= 2) {
            rName = rsSplit[0];
            sName = rsSplit[1];
        }
        String uName = null;
        if (split.length >= 2) {
            uName = split[1].split("】")[0];
        }
        res.add(rName);
        res.add(sName);
        res.add(uName);
        return res;
    }
}
