package com.shenji.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI {

	@Test
	public void testWrite03() throws Exception{
		//1.创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.创建工作表
		HSSFSheet sheet = workbook.createSheet("testname");
		//1.创建行
		HSSFRow row = sheet.createRow(3);
		//1.创建单元格
		HSSFCell cell = row.createCell(3);
		
		cell.setCellValue("hello world");
		
		//输出到硬盘
//		File file = new File();
		FileOutputStream outputStream = new FileOutputStream("D:\\outPut\\测试导出.xls");
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
	@Test
	public void testRead03() throws Exception{
		
		FileInputStream inputStream = new FileInputStream("D:\\outPut\\测试导出.xls");
		//1.读取工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		//1.读取工作表
		//HSSFSheet sheet = workbook.createSheet("testname");
		HSSFSheet sheet = workbook.getSheetAt(0);
		//1.读取行
		//HSSFRow row = sheet.createRow(3);
		HSSFRow row = sheet.getRow(3);
		//1.读取单元格
//		HSSFCell cell = row.createCell(3);
		HSSFCell cell = row.getCell(3);
		
		//cell.setCellValue("hello world");
		System.out.println(cell.getStringCellValue());
		//输出到硬盘
//		File file = new File();
		//FileOutputStream outputStream = new FileOutputStream("D:\\outPut\\测试导出.xls");
	//	workbook.write(outputStream);
		workbook.close();
	//	outputStream.close();
		inputStream.close();
		
		
	}
	@Test
	public void testWrite07() throws Exception{
		//1.创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//1.创建工作表
		XSSFSheet sheet = workbook.createSheet("testname");
		//1.创建行
		XSSFRow row = sheet.createRow(3);
		//1.创建单元格
		XSSFCell cell = row.createCell(3);
		
		cell.setCellValue("hello world 07");
		
		//输出到硬盘
//		File file = new File();
		FileOutputStream outputStream = new FileOutputStream("D:\\outPut\\测试导出07.xls");
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
	@Test
	public void testRead07() throws Exception{
		
		FileInputStream inputStream = new FileInputStream("D:\\outPut\\测试导出07.xls");
		//1.读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		//1.读取工作表
		//XSSFSheet sheet = workbook.createSheet("testname");
		XSSFSheet sheet = workbook.getSheetAt(0);
		//1.读取行
		//XSSFRow row = sheet.createRow(3);
		XSSFRow row = sheet.getRow(3);
		//1.读取单元格
//		XSSFCell cell = row.createCell(3);
		XSSFCell cell = row.getCell(3);
		
		//cell.setCellValue("hello world");
		System.out.println(cell.getStringCellValue());
		//输出到硬盘
//		File file = new File();
		//FileOutputStream outputStream = new FileOutputStream("D:\\outPut\\测试导出.xls");
		//	workbook.write(outputStream);
		workbook.close();
		//	outputStream.close();
		inputStream.close();
		
		
	}
}
