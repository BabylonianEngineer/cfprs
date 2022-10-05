package com.hf.cfprs.service;

import com.hf.cfprs.service.ExcelService;
import com.hf.cfprs.utils.SheetUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:hanfei
 */
@SpringBootTest
class ExcelServiceTest {

    @Autowired
    private ExcelService excelService;

    private String p1 = "C:\\Users\\Dell\\Desktop\\Excels\\30万吨／年碳酸二甲酯、5万吨／年碳酸丙烯酯暨5万吨／年碳酸乙烯酯项目.xls";
    private String p2 = "C:\\Users\\Dell\\Desktop\\Excels\\金康花园安置小区工程.xls";

    //pn 格式错误，有问题,将拒绝解析
    private String pn = "C:\\Users\\Dell\\Desktop\\Excels\\鼎峰小区二期（新筑巴伦西亚·鼎峰二期）.xls";

    //pn1、pn2 新格式
    private String pn1 = "C:\\Users\\Dell\\Desktop\\Excels\\宜宾临港经济技术开发区宜宾会展中心二期项目.xls";
    private String pn2 = "C:\\Users\\Dell\\Desktop\\Excels\\武胜县仁和小学建设项目.xls";
    private String pn08 = "C:\\Users\\Dell\\Desktop\\Excels\\武胜县体育馆棚户区改造安置房建设项目.xls";
    private String pn2_1 = "C:\\Users\\Dell\\Desktop\\Excels\\四川黑龙滩国际生态旅游度假区安置房项目南A区.xls";


    @Test
    public void parsePathMultiSheetExcel() throws Exception {
        File file = new File(pn2);
        excelService.parsePathMultiSheetExcel(file);
    }

    @Test
    void getType() {
        String str1 = "复件A3 分部分项工程量清单计价表(带人材机、综合费、措施、~";
        String str2 = "E.3 单位工程投标报价汇总表(封-2)--改【安装工程】";
        String str2_1 = "C.2 工程项目招标控制价扉页(扉-2-1)";
        String str3 = "建设项目投报价汇总表(表-02)";
        String type = SheetUtil.getTypeCode(str3);
        System.out.println(type);

    }

    @Test
        //ok
    void parseCover() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn1, "2");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseCover(sheetForTest);

    }

    @Test
        //ok
    void parseSingle() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn2, "03");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseSingle(sheetForTest);

    }

    @Test
        //ok
    void parseUnit() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn1, "04");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseUnit(sheetForTest);

    }

    @Test
        //ok
    void parseCommonTissue() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn08, "08");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseCommonTissue(sheetForTest);

    }

    @Test
    void parseCommonAlterTissue() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn2, "08改");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseCommonAlterTissue(sheetForTest);

    }

    @Test
        //ok
    void parseTotalMeasureTissue() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn2, "11");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseTotalMeasureTissue(sheetForTest);

    }

    @Test
        //ok
    void parseOtherTissue() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn2, "12");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseOtherTissue(sheetForTest);

    }

    @Test
        //ok
    void parseTaxTissue() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn2, "13");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseTaxTissue(sheetForTest);

    }

    @Test
        //ok
    void parseAttachedTissue() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn2, "复件");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseAttachedTissue(sheetForTest);
    }

    @Test
//ok
    void parseMainMaterialSheet() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn2, "22");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseMainMaterialSheet(sheetForTest);
    }

    @Test
        //ok 新格式中没有21类型的表
    void parseMaterialSheet() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(p1, "21");
        SheetUtil.displaySheetContent(sheetForTest);
        excelService.parseMaterialSheet(sheetForTest);
    }

    @Test
    void cellToDouble() {
        String s = "672131.19";
        Double d = Double.parseDouble(s);
        System.out.println(d);
    }

    @Test
    void checkTissue() {
        String value = "1j";
        Pattern p = Pattern.compile("\\d+.");
        Matcher m = p.matcher(value);
        boolean flag = m.find();
        System.out.println("^" + flag);

    }

    @Test
    void checkTaxTCellInfo() {
        String value = "100";
        Pattern p = Pattern.compile("^\\d+$");//全数字，没有其他字符
        Matcher m = p.matcher(value);
        System.out.println("^" + m.find());


    }

    @Test
    void checkCTCellInfoEnd() {
        System.out.println(checkCTCellInfoEndTest(""));
        System.out.println(checkCTCellInfoEndTest(" "));
//        System.out.println(excelService.checkCTCellInfoEndTest(null));
        System.out.println(checkCTCellInfoEndTest("小计"));
        System.out.println(checkCTCellInfoEndTest("分部小计"));
        System.out.println(checkCTCellInfoEndTest("计"));


    }

    public boolean checkCTCellInfoEndTest(String str) {
//        String value=row.getCell(3).toString().trim();
        String value = str.trim();
        if (value.length() < 2) {
            return false;
        }
        return value.substring(value.length() - 2).equals("小计");
//        return row.getCell(3).toString().trim().equals("分部小计");
    }

    @Test
    void cutSName() {
        String name = "金康花园安置小区工程\\配套道路工程【道路工程】";
        String[] split = name.split("\\\\");
        String rName = split[0];
        String[] split1 = split[split.length - 1].split("【");

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < split.length - 1; i++) {
            sb.append(split[i]).append("\\");
        }
        String sName = sb.append(split1[0]).toString();

        System.out.println(sName);
    }

    @Test
    void splitRegexTest() {
        String name = "金康花园安置小区工程—配套道路工程【道路工程】";
        String[] split = name.split("[\\—【】]");
        for (String s : split) {
            System.out.println("^" + s);
        }
    }

    @Test
    void getRSUName() {
//        String[] split = sheet.getRow(1).getCell(2).getStringCellValue().split("【");
//        String s="金康花园\\安置小区工程\\配套道路工程【道路工程】";
        String s = "金信•壹号公馆\\6#楼 ";
        String[] split = SheetUtil.cleanStr(s).split("【");

//        String[] split = SheetUtil.cleanStr(sheet.getRow(1).getCell(2).getStringCellValue()).split("【");
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
        System.out.println("^" + rName + "^");
        System.out.println("^" + sName + "^");
        System.out.println("^" + uName + "^");

    }

}
