package com.hf.cfprs.controller;

import com.hf.cfprs.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author:hanfei
 */
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity uploadParseExcel(@RequestParam("file") MultipartFile file) throws Exception {
        //Excel文件格式检查
        Boolean accept = excelService.fileFormatCheck(file);
        if (!accept) {
            return new ResponseEntity<>(file.getOriginalFilename() + "内容格式不支持解析", HttpStatus.BAD_REQUEST);
        }
        try {
            excelService.parseWebMultiSheetExcel(file);
        } catch (Exception e) {
            String errInfo = e.getMessage() + "<<<系统异常>>>解析此Excel时产生:" + file.getOriginalFilename();
            log.error(errInfo);
            return new ResponseEntity<>(errInfo, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(file.getOriginalFilename() + "上传解析成功", HttpStatus.OK);
    }


}
