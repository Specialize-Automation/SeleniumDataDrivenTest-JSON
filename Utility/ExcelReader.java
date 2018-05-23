package com.aditya.testData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;


public class ExcelReader 
{
		static XSSFWorkbook wb;
		static XSSFSheet sheet;

		public static String[][] getExcelData(String excelPath,String sheetName) throws IOException
		{
				String dataSets[][] = null;
				File excelFile = new File(excelPath);
				FileInputStream fis = new FileInputStream(excelFile);
				
				wb = new XSSFWorkbook(fis);
				sheet = wb.getSheet(sheetName);
				
				int totalRow = sheet.getLastRowNum()+1;  //row start from 0
				int totalColumn = sheet.getRow(0).getLastCellNum();
				
//array start from 0, as (0,1)(0,2) so creating array with [totalRow-1][totalColumn]
				dataSets = new String [totalRow-1][totalColumn];
				Iterator<Row> rowIterator = sheet.iterator();
				
				int i = 0;
				int j = 0;
				while(rowIterator.hasNext())
				{
					Row row = rowIterator.next();
					if(i++!=0)
					{
						int k = j; //to ignore the 1st header row
						j++;
						//for each row now iterate through the column
						Iterator<Cell> cellIterator = row.cellIterator();
						int c = 0;
						while(cellIterator.hasNext())
						{
							Cell cell = cellIterator.next();
							if(cell.getCellTypeEnum()==CellType.NUMERIC)
							{
								// cell.getNumericCellValue() is double so converting it to string
								dataSets[k][c++]=String.valueOf(cell.getNumericCellValue());
							}else
							if(cell.getCellTypeEnum()==CellType.STRING)
							{
								dataSets[k][c++]=cell.getStringCellValue();
								//System.out.println(cell.getStringCellValue());	
							}
						}
					}
				}
				fis.close();
				return dataSets;
		}
}







/*		public int getRowCount(String sheetName)
		{
			int row = wb.getSheet(sheetName).getLastRowNum();
			row++;
			return row;
		}


		public int getColumnCount(String sheetName)
		{
			int column = wb.getSheet(sheetName).getRow(0).getLastCellNum();
			return column;
		}*/


