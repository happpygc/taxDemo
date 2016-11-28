package com.shenji.core.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.shenji.nsfw.user.entity.User;

public class ExcelUtils {
	
	public static void exportUserExcel(List<User> userList,
			ServletOutputStream outputStream){
		
		try {
			//1.创建工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();

			//1.1、创建合并单元格对象
				CellRangeAddress rangeAddress = new CellRangeAddress(0,0,0,4);
			//1.2、头标题样式
				HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			//1.3、列标题样式
				HSSFCellStyle style2 = createCellStyle(workbook, (short)16);
				
			//
			//2、创建工作表
				HSSFSheet sheet = workbook.createSheet("用户列表");
				
			//2.1、加载合并单元格对象
				sheet.addMergedRegion(rangeAddress);
				sheet.setDefaultColumnWidth(20);
			//3、创建行
				
			//3.1、创建头标题行；并且设置头标题
				HSSFRow row1 = sheet.createRow(0);
				HSSFCell cellHead = row1.createCell(0);
				//加载头的样式
				cellHead.setCellStyle(style1);
				cellHead.setCellValue("用户列表");
			//3.2、创建列标题行；并且设置列标题
				HSSFRow row2 = sheet.createRow(1);
				String [] titles = {"用户名","账号","所属部门","性别","电子邮箱"};
				for(int i=0;i<titles.length;i++){
					HSSFCell cell2 = row2.createCell(i);
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
			//4、操作单元格；将用户列表写入excel
				if(userList != null){
					for(int j=0;j<userList.size();j++){
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell11 = row.createCell(0);
						cell11.setCellValue(userList.get(j).getName());
						HSSFCell cell12 = row.createCell(1);
						cell12.setCellValue(userList.get(j).getAccount());
						HSSFCell cell13 = row.createCell(2);
						cell13.setCellValue(userList.get(j).getDept());
						HSSFCell cell14 = row.createCell(3);
						cell14.setCellValue(userList.get(j).isGender()?"男":"女");
						HSSFCell cell15 = row.createCell(4);
						cell15.setCellValue(userList.get(j).getEmail());
					}
				}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short s) {

		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints(s);
		//加载字体
		style.setFont(font);
		return style;
	}

}
