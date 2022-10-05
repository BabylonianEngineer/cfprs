package com.hf.cfprs.utils;

import com.hf.cfprs.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author:hanfei
 */
@SpringBootTest
class SheetUtilTest {

    @Autowired
    public ExcelService excelService;

    //pn 此文件 格式有误
    private String pn = "C:\\Users\\Dell\\Desktop\\Excels\\鼎峰小区二期（新筑巴伦西亚·鼎峰二期）.xls";

    //新正确格式
    private String pn1 = "C:\\Users\\Dell\\Desktop\\Excels\\宜宾临港经济技术开发区宜宾会展中心二期项目.xls";
    private String pn2 = "C:\\Users\\Dell\\Desktop\\Excels\\武胜县仁和小学建设项目.xls";

    @Test
    void formatCheck() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn1, "03");
        SheetUtil.displaySheetContent(sheetForTest);
        //格式检查
        boolean b = SheetUtil.formatCheck(sheetForTest);
        System.out.println("^"+b);
    }

    @Test
    void cleanStr() {
        String str="安 全 文 明\n" +
                " 施 工费 ";
        System.out.println(str);
        System.out.println(SheetUtil.cleanStr(str));
    }

    @Test
    void getRSUName() throws Exception {
        HSSFSheet sheetForTest = SheetUtil.getSheetForTest(pn1, "04");
        SheetUtil.displaySheetContent(sheetForTest);
        List<String> names=SheetUtil.getRSUName(sheetForTest,1,2);
        for(String s:names){
            System.out.println("^"+s+"^");
        }
    }
}
