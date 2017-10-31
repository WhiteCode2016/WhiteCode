package com.whitecode.poi;

import com.whitecode.quartz.model.ScheduleJob;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by White on 2017/10/25.
 */
public class PoiTest {

    public static void main(String[] args) throws Exception {
//        String tmpFile = "classpath:template.doc";
//        String expFile = "E:/temp/result.doc";
//        Map<String, String> datas = new HashMap<String, String>();
//        datas.put("title", "标题部份");
//        datas.put("content", "这里是内容，测试使用POI导出到Word的内容！");
//        datas.put("author", "White");
//        exportDocByTemplate(ResourceUtils.getFile(tmpFile), datas, expFile);
//        System.out.println("导出.doc文档成功");

//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet("sheet0");
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell = row.createCell(0);
//        cell.setCellValue("测试案例");
//        FileOutputStream outputStream = new FileOutputStream("E:/temp/workbook.xls");
//        wb.write(outputStream);
//        outputStream.flush();
//
//        List<ScheduleJob> scheduleJobs = new ArrayList<>();
//        ScheduleJob scheduleJob = new ScheduleJob();
//        scheduleJob.setJobId("100");
//        scheduleJob.setJobName("test");
//        ScheduleJob scheduleJob1 = new ScheduleJob();
//        scheduleJob1.setJobId("101");
//        scheduleJob1.setJobName("test1");
//        scheduleJobs.add(scheduleJob);
//        scheduleJobs.add(scheduleJob1);
//
//        String headers[] = {"编号","任务名称"};
//        String columns[] = {"jobId","jobName"};
//        FileOutputStream outputStream = new FileOutputStream("E:/temp/workbook.xls");
//        PoiManager poiUtil = new PoiManager();
//        poiUtil.exportExcel("job信息",headers,columns,scheduleJobs,outputStream,"yyyy-MM-dd",5);

        PoiManager poiUtil = new PoiManager();
        poiUtil.importExcel("C:/Users/White/Downloads/C-DL007-1710301.xlsx");

//        String tempFilePath = "C:/Users/White/Downloads/template.xlsx";
//        String expotFilePath = "E:/temp/result.xlsx";
//        Map<String, String> datas = new HashMap<String, String>();
//        datas.put("head","this is head");
//        PoiManager.exportExcelByTemplate(tempFilePath,datas,expotFilePath);

    }
}
