package com.whitecode.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * POI数据导入/导出工具类
 * Created by White on 2017/10/25.
 */
public class PoiManager<T> {
    private static final Logger logger = LoggerFactory.getLogger(PoiManager.class);
    private static final String EXCEL2003 = ".xls";  //2003- 版本的excel
    private static final String EXCEL2007 = ".xlsx"; //2007+ 版本的excel

    /**
     * 按模板导出.doc文档
     * @param tmpFlie 模板文档路径（template.doc）
     * @param contentMap 文档内容
     * @param exportFile 导出文档路径
     * @throws Exception
     */
    private static void exportDocByTemplate(File tmpFlie, Map<String,String> contentMap, String exportFile) throws Exception {
        FileInputStream tempFileInputStream = new FileInputStream(tmpFlie);
        HWPFDocument document = new HWPFDocument(tempFileInputStream);
        // 读取文本内容
        Range bodyRange = document.getRange();
        // 替换内容
        for (Map.Entry<String,String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}",entry.getValue());
        }
        // 导出文件
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        OutputStream outputStream = new FileOutputStream(exportFile);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.close();
    }

    /**
     * 按照模板导出.xls/.xlsx文档
     * @param tempFilePath
     * @param exportFilePath
     * @throws Exception
     */
    public static void exportExcelByTemplate(String tempFilePath,Map<String,String> contentMap,String exportFilePath) throws Exception {
        if (tempFilePath != null && !tempFilePath.equals("")) {
//            String ext = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            // 获取文件后缀名
            String ext = tempFilePath.substring(tempFilePath.lastIndexOf("."));
            if (!ext.equals(EXCEL2003) && !ext.equals(EXCEL2007)) {
                logger.info("文件后缀名不是.xls或.xlsx");
                return;
            }
        }
        InputStream inputStream = new FileInputStream(tempFilePath);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        for (Map.Entry<String,String> entry : contentMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    /**
     * 导出excel（多个sheet）
     * @param title sheet名称
     * @param headers 表头
     * @param columns 显示的字段
     * @param result  结果集
     * @param out 输出流
     * @param pattern 时间格式
     * @param N 每个sheet数据行数
     */
    public void exportExcel(String title, String[] headers, String[] columns,
                            List<T> result, OutputStream out, String pattern, int N)
            throws Exception {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = getHeaderDefaultStyle(workbook);
        HSSFCellStyle titleStyle = getTitleDefaultStyle(workbook);

        /*
         * 定义注释的大小和位置,详见文档 HSSFComment comment = patriarch.createComment(new
         * HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5)); 设置注释内容
         * comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
         * 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容. comment.setAuthor("leno");
         */

        int totalSize = result.size(); // 结果集总记录数
        for (int sheetNum = 0; sheetNum < totalSize / N + 1; sheetNum++) {
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet(title + (sheetNum + 1));

            // 设置表格默认列宽度为20个字节
            sheet.setDefaultColumnWidth((short) 20);

            // 声明一个画图的顶级管理器
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

//            sheet.addMergedRegion(new Region(0, (short) 0, 0,
//                    (short) (headers.length - 1)));// 指定合并区域
            sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0,
                    (short) (headers.length - 1)));// 指定合并区域
            HSSFRow rowHeader = sheet.createRow(0);
            HSSFCell cellHeader = rowHeader.createCell((short) 0); // 只能往第一格子写数据，然后应用样式，就可以水平垂直居中
            HSSFRichTextString textHeader = new HSSFRichTextString(title);
            cellHeader.setCellStyle(titleStyle);
            cellHeader.setCellValue(textHeader);

            HSSFRow row = sheet.createRow(1);
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell((short) i);
                cell.setCellStyle(style);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            // 遍历集合数据，产生数据行
            if (result != null) {
                int index = 2;
                for (int m = sheetNum * N ; m < (sheetNum + 1) * N && m < totalSize; m++) {
                    T t = result.get(m);
                    row = sheet.createRow(index);
                    index++;
                    for (short i = 0; i < columns.length; i++) {
                        HSSFCell cell = row.createCell(i);
                        String fieldName = columns[i];
                        String getMethodName = "get"
                                + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1);
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                        Object value = getMethod.invoke(t, new Class[] {});
                        String textValue = null;
                        if (value == null) {
                            textValue = "";
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof byte[]) {
                            // 有图片时，设置行高为60px;
                            row.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(i, (short) (35.7 * 80));
                            // sheet.autoSizeColumn(i);
                            byte[] bsValue = (byte[]) value;
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0,
                                    0, 1023, 255, (short) 6, index, (short) 6, index);
                            anchor.setAnchorType(ClientAnchor.AnchorType.byId(2));
                            patriarch.createPicture(anchor, workbook
                                    .addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }

                        if (textValue != null) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                HSSFRichTextString richString = new HSSFRichTextString(
                                        textValue);
                                cell.setCellValue(richString);
                            }
                        }
                    }
                }
            }
        }
        workbook.write(out);

        // 清空并关闭输出流
        out.flush();
        out.close();
    }

    /**
     * 导出excel（单个sheet）
     * @param title
     * @param headers
     * @param columns
     * @param result
     * @param out
     * @param pattern
     * @throws Exception
     */
    public void exportExcel(String title, String[] headers, String[] columns,
                            List<T> result, OutputStream out, String pattern) throws Exception {
        // 声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle headerStyle = getHeaderDefaultStyle(workbook);
        HSSFCellStyle titleStyle = getTitleDefaultStyle(workbook);

        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽为20个字节
        sheet.setDefaultColumnWidth(20);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 指定合并第一行区域
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,headers.length-1));
        // 设置Title
        HSSFRow rowTitle = sheet.createRow(0);
        HSSFCell cellTitle = rowTitle.createCell(0);
        HSSFRichTextString textTitle = new HSSFRichTextString(title);
        cellTitle.setCellStyle(titleStyle);
        cellTitle.setCellValue(textTitle);
        // 设置Headers
        HSSFRow rowHeader = sheet.createRow(1);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cellHeader = rowHeader.createCell(i);
            HSSFRichTextString textHeader = new HSSFRichTextString(headers[i]);
            cellHeader.setCellStyle(headerStyle);
            cellHeader.setCellValue(textHeader);
        }
        // 结果集总记录数
        int totalSize = result.size();
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 2;
            for (int m = 0; m < totalSize; m++) {
                T t = result.get(m);
                HSSFRow rowResult = sheet.createRow(index);
                index++;
                for (short i = 0; i < columns.length; i++) {
                    HSSFCell cell = rowResult.createCell(i);
                    String fieldName = columns[i];
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                    Object value = getMethod.invoke(t, new Class[] {});
                    String textValue = null;
                    if (value == null) {
                        textValue = "";
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        rowResult.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0,
                                0, 1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(ClientAnchor.AnchorType.byId(2));
                        patriarch.createPicture(anchor, workbook
                                .addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                    }

                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(textValue);
                            cell.setCellValue(richString);
                        }
                    }
                }
            }
        }
        workbook.write(out);
        out.flush();
        out.close();
    }

    /**
     * 设置header样式
     * @param workbook
     * @return
     */
    private HSSFCellStyle getHeaderDefaultStyle(HSSFWorkbook workbook) {
        // 生成一个样式
        HSSFCellStyle HeaderStyle = workbook.createCellStyle();
        // 设置背景色
        HeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        HeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置边框
        HeaderStyle.setBorderBottom(BorderStyle.THIN);
        HeaderStyle.setBorderLeft(BorderStyle.THIN);
        HeaderStyle.setBorderRight(BorderStyle.THIN);
        HeaderStyle.setBorderTop(BorderStyle.THIN);
        // 设置居中
        HeaderStyle.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        HSSFFont headerFont = workbook.createFont();
        // 设置字体样式
        headerFont.setColor(IndexedColors.BLACK.index);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        // 把字体应用到当前样式
        HeaderStyle.setFont(headerFont);
        // 指定当单元格内容显示不下时自动换行
        HeaderStyle.setWrapText(true);
        return HeaderStyle;
    }

    /**
     * 设置title样式
     * @param workbook
     * @return
     */
    private HSSFCellStyle getTitleDefaultStyle(HSSFWorkbook workbook) {
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        // 水平居中
        titleStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        // 垂直居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体
        HSSFFont titleFont = workbook.createFont(); // 创建字体对象
        titleFont.setFontHeightInPoints((short) 16); // 设置字体大小
        titleFont.setBold(true);// 设置粗体
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    /**
     * 解析excel，并保存到数据库
     * @param filePath 文件路径
     * @throws Exception
     */
    public void importExcel(String filePath) throws Exception {
        if (filePath != null && !filePath.equals("")) {
//            String ext = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            // 获取文件后缀名
            String ext = filePath.substring(filePath.lastIndexOf("."));
            if (!ext.equals(EXCEL2003) && !ext.equals(EXCEL2007)) {
                logger.info("文件后缀名不是.xls或.xlsx");
                return;
            }
        }
        InputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(inputStream);
        // 遍历sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            // 遍历每个sheet的行数
            for (int rowNum = 2; rowNum < sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    // 进行入库操作
//                    System.out.println(row.getCell(0));
//                    System.out.println(row.getCell(1));
                    System.out.println(getCellValue(row.getCell(0)));
                    System.out.println(getCellValue(row.getCell(1)));
                }
            }
        }
    }

    /**
     * 判断excel的数据类型
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        if (cellType.equals(CellType.NUMERIC)) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cellType.equals(CellType.BOOLEAN)) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cellType.equals(CellType.STRING)) {
            return cell.getStringCellValue();
        } else if (cellType.equals(CellType.BLANK)) {
            return "";
        } else if (cellType.equals(CellType.FORMULA)) {
            return cell.getCellFormula();
        } else {
            return "";
        }
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    private String getMergedRegionValue(Sheet sheet,int row,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount;i++) {
            CellRangeAddress rangeAddress = sheet.getMergedRegion(i);
            int firstColumn = rangeAddress.getFirstColumn();
            int lastColumn = rangeAddress.getLastColumn();
            int firstRow = rangeAddress.getFirstRow();
            int lastRow = rangeAddress.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row nRow = sheet.getRow(firstRow);
                    Cell nCell = nRow.getCell(firstColumn);
                    return getCellValue(nCell);
                }
            }
        }
        return null;
    }

    /**
     * 判断是否合并了行
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    private boolean isMergedRow(Sheet sheet,int row,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount;i++) {
            CellRangeAddress rangeAddress = sheet.getMergedRegion(i);
            int firstColumn = rangeAddress.getFirstColumn();
            int lastColumn = rangeAddress.getLastColumn();
            int firstRow = rangeAddress.getFirstRow();
            int lastRow = rangeAddress.getLastRow();
            if (row == firstRow && row == lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private boolean isMergedRegion(Sheet sheet,int row ,int column) {

        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {

            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row > firstRow && row < lastRow){
                if(column > firstColumn && column < lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断sheet页中是否含有合并单元格
     * @param sheet
     * @return
     */
    private boolean hasMerged(Sheet sheet) {
        return sheet.getNumMergedRegions() > 0 ? true : false;
    }

    /**
     * 解析Excel测试案例
     * @param filePath
     * @throws Exception
     */
    public void importExcelTest(String filePath) throws Exception {
        InputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(inputStream);
        // 遍历sheet
//        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(4);
           logger.info( sheet.getSheetName());
            // 遍历每个sheet的行数
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                for (Cell cell : row) {
                    boolean isMerge = isMergedRegion(sheet,rowNum,cell.getColumnIndex());
                    if (isMerge) {
                        String rs = getMergedRegionValue(sheet,row.getRowNum(),cell.getColumnIndex());
                        System.out.println(rs);
                    }else {
                        System.out.println(getCellValue(cell)+"");
                    }
                }
            }
//        }
    }
}


