package com.android;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取本地的一个excel文档，并输出到控制台
 */
public class ReadLocalExcel {

    //总行数
    private int totalRows = 0;
    //总列数
    private int totalCells = 0;
    //错误信息
    private  String errorInfo;

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    //检查文件是否为excel或者为空
    public boolean validateExcel(String filePath){
        //检查文件格式
        if (filePath==null || !(UUtil.isExcel2003(filePath) || UUtil.isExcel2007(filePath))) {
            errorInfo = "不是excel格式";
            return false;
        }
        //检查文件是否存在
        File file = new File(filePath);
        if(file==null || !file.exists()){
            errorInfo = "文件不存在";
            return  false;
        }
        return  true;

    }


    public List<List<String>> read(String filePath){
        List<List<String>> dataList = new ArrayList<List<String>>();
        InputStream is = null;

        try {
            //验证文件
            if(!validateExcel(filePath)){
                System.out.println(errorInfo);
                return null;
            }

            //判断文件类型
            boolean isExcel2003 = true;
            if(UUtil.isExcel2007(filePath)){
                isExcel2003 = false;
            }

            //调用读取方法
            File file = new File(filePath);
            is = new FileInputStream(file);
            dataList = read(is,isExcel2003);
            is.close();

        }catch (Exception e ){

        }finally {

        }
        return  dataList;
    }


    public List<List<String>> read(InputStream inputStream,boolean isExcel2003) throws  Exception{
        List<List<String>> dataLst = null;

        Workbook wb = null;
        if(isExcel2003){
            wb = new HSSFWorkbook(inputStream);
        }else {
            wb = new XSSFWorkbook(inputStream);
        }

        dataLst =readWork(wb);
        return  dataLst;
    }

    public List<List<String>> readWork(Workbook wb){
        List<List<String>> dataList = new ArrayList<List<String>>();
        //得到第一个shell
        Sheet sheet =wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if(this.totalRows>=1 && sheet.getRow(0)!=null){
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        //循环excel的行
        for (int r=0;r<this.totalRows;r++){
            Row row = sheet.getRow(r);
            if(row == null){
                continue;
            }

            List<String> rowList = new ArrayList<String>();
            //循环excel的列
            for(int c=0;c<this.getTotalCells();c++){
                Cell cell = row.getCell(c);
                String cellValue = "";
                if(null !=cell){
                    //判断数据类型
                    switch (cell.getCellType()){
                        case HSSFCell.CELL_TYPE_NUMERIC: //数字
                            //cellValue =cell.getNumericCellValue()+"";
                            if(String.valueOf(cell.getNumericCellValue()).indexOf("E")==-1){
                                cellValue= String.valueOf(cell.getNumericCellValue());
                            }else {
                                cellValue= new DecimalFormat("#").format(cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_STRING:  //字符串
                            cellValue = cell.getStringCellValue();
                            break;
                        case  HSSFCell.CELL_TYPE_BOOLEAN: //布尔
                            cellValue = cell.getBooleanCellValue()+"";
                            break;
                        case  HSSFCell.CELL_TYPE_FORMULA: //公式
                            cellValue = cell.getCellFormula()+"";
                            break;
                        case HSSFCell.CELL_TYPE_BLANK: //空
                            cellValue = "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:  //故障
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }

                rowList.add(cellValue);
            }
            dataList.add(rowList);
        }
        return  dataList;
    }


}


class UUtil{
    public static boolean isExcel2003(String filePath){
        return  filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
