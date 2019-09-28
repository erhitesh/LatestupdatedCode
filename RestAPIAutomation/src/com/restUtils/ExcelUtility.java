package com.restUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.restAssuredUtilities.GlobalParam;

public class ExcelUtility {
	
	public static String excelSheetName = "";
	public static String testDataExcelPath = null;
	private static XSSFWorkbook excelWBook;
	private static XSSFSheet excelWSheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	public static int rowNumber;
	public static int colNumber;
	
	public static int getRowNumber() {
		return rowNumber;
	}
	
	public static void setRowNumber(int rowNumber) {
		ExcelUtility.rowNumber = rowNumber;
	}
	
	public static int getColumnNumber() {
		return colNumber;
	}
	
	public static void setColumnNumber(int columnNumber) {
		ExcelUtility.colNumber = columnNumber;
	}
	
	public static void setExcelFileSheet(String sheetName) {
		testDataExcelPath = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "src" + GlobalParam.slash + "Configuration";
		try {
			FileInputStream excelFile = new FileInputStream(testDataExcelPath + ".xlsx");
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	public static String getCellData(int rowNum, int colNum) {
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		String cellData = new DataFormatter().formatCellValue(cell);
		
		return cellData;
	}
	
	public static void setCellData(int rowNum, int colNum, String value) {
		try {
			row = excelWSheet.getRow(rowNum);
			cell = row.getCell(colNum);
			
			if (cell == null) {
				row.createCell(colNum);
			}
			
			cell.setCellValue(value);
			FileOutputStream fos = new FileOutputStream(testDataExcelPath + excelSheetName);
			excelWBook.write(fos);
			fos.flush();
			fos.close();
		} catch (IOException io) {
		}
	}
	
	public static XSSFRow getRowData(int rowNum) {
		return excelWSheet.getRow(rowNum);
	}
}
