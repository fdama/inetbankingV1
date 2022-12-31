package com.internetBanking.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class XLUtils {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static HSSFWorkbook wb;
	public static HSSFSheet ws;
	public static HSSFRow row;
	public static HSSFCell cell;
	
	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new HSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new HSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
	}
	
	public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new HSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		String data;
		try {
			String cellData = new DataFormatter().formatCellValue(cell);
			return cellData;
		}
		catch(Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
		
	}
	
	public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data) throws IOException{
		fi = new FileInputStream(xlFile);
		wb = new HSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		cell.setCellValue(data);
		wb.write(new FileOutputStream(xlFile));
		wb.close();
		fi.close();
		fo.close();
	}
	
	
	
	

	

}












