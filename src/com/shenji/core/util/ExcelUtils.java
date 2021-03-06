package com.shenji.core.util;

import java.io.File;
import java.io.FileInputStream;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shenji.nsfw.user.dao.UserDao;
import com.shenji.nsfw.user.entity.User;


public class ExcelUtils {
	
	//导出用户表
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

	//导入用户表
	public static void importUserExcel(UserDao userDao,File userExcel, String userExcelFileName){
		try {
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			FileInputStream fis = new FileInputStream(userExcel);
			//读取工作簿
			Workbook workbook = is03Excel?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
			//读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getPhysicalNumberOfRows() > 2){
				User user = null;
				for(int k = 2; k < sheet.getPhysicalNumberOfRows(); k++){
					//4、读取单元格
					Row row = sheet.getRow(k);
					user = new User();
					//用户名
					Cell cell0 = row.getCell(0);
					user.setName(cell0.getStringCellValue());
					//帐号
					Cell cell1 = row.getCell(1);
					user.setAccount(cell1.getStringCellValue());
					//所属部门
					Cell cell2 = row.getCell(2);
					user.setDept(cell2.getStringCellValue());
					//性别
					Cell cell3 = row.getCell(3);
					user.setGender(cell3.getStringCellValue().equals("男"));
					//手机号
					String mobile = "";
					Cell cell4 = row.getCell(4);
					try {
						mobile = cell4.getStringCellValue();
					} catch (Exception e) {
						double dMobile = cell4.getNumericCellValue();
						mobile = BigDecimal.valueOf(dMobile).toString();
					}
					user.setMobile(mobile);
					
					//电子邮箱
					Cell cell5 = row.getCell(5);
					user.setEmail(cell5.getStringCellValue());
					//生日
					Cell cell6 = row.getCell(6);
					if(cell6.getDateCellValue() != null){
						user.setBirthday(cell6.getDateCellValue());
					}
					//默认用户密码为 123456
					user.setPassword("123456");
					//默认用户状态为 有效
					user.setState(User.USER_STATE_VALID);
					
					//5、保存用户
					userDao.save(user);
				}
			}
			workbook.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//创建单元格的样式
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
