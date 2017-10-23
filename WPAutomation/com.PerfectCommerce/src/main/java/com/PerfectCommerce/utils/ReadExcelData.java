package com.PerfectCommerce.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	private static final ReadExcelData instance = new ReadExcelData();
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static Cell cell;
	private static Row row;
	private static String sheetName;

	public static synchronized ReadExcelData getInstance(String sheetName) throws IOException {
		ReadExcelData.sheetName=sheetName;
		File file = new File(ReadConfig.getInstance().getExcelPath());
		FileInputStream fileInput = new FileInputStream(file);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(sheetName);
		fileInput.close();
		return instance;
	}

	public String getStringValue(String strRow) throws IOException {
		System.out.println(sheet);
		final DataFormatter df = new DataFormatter();
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 0; i < rowCount + 1; i++) {

			row = sheet.getRow(i);
			
			for (int j = 0; j < row.getLastCellNum(); j++) {
				//System.out.println(row.getCell(j).getStringCellValue().toString());
				
				if (df.formatCellValue(row.getCell(j)).equals(strRow)) {
					 cell = row.getCell(j+1);
					return df.formatCellValue(cell);
				} else {
					continue;
				}
			}
		}
	
		
		return null;
	}
	
	public void updateCellValue(String strRow,String value) throws IOException {
		getInstance(sheetName).getStringValue(strRow);
		cell.setCellValue(value);
		FileOutputStream outputStream = new FileOutputStream(ReadConfig.getInstance().getExcelPath());
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();//Close in finally if possible
		}

}
	
