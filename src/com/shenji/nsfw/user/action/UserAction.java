package com.shenji.nsfw.user.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.shenji.core.action.BaseAction;
import com.shenji.nsfw.user.entity.User;
import com.shenji.nsfw.user.service.UserService;

public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	
	

	private List<User> userList;
	private User user;

	//上传头像相关
	private File headImg;
	private String headImgFileName;
	private String headImgContenType;
	//导出相关
	private File userExcel;
	private String userExcelFileName;
	private String userExcelContenType;
	
	//列表页面
	public String listUI(){
		 userList = userService.findObjects();
		 return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(user!=null){
				if(headImg !=null){
					String prepath = ServletActionContext.getServletContext().getRealPath("/upload/user");
					String fileName = UUID.randomUUID().toString().replace("-","")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					FileUtils.copyFile(headImg, new File(prepath,fileName));
					user.setHeadImg("user/"+fileName);
				}
				
				userService.save(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		if (user != null && user.getId() != null) {
			user = userService.findObjectById(user.getId());
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(user!=null){
				if(headImg !=null){
					String prepath = ServletActionContext.getServletContext().getRealPath("/upload/user");
					String fileName = UUID.randomUUID().toString().replace("-","")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					FileUtils.copyFile(headImg, new File(prepath,fileName));
					user.setHeadImg("user/"+fileName);
				}
				
				userService.update(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//删除
	public String delete(){
		if(user != null && user.getId() != null){
			userService.delete(user.getId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	
	//导出
	public void exportExcel(){
		
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;fileName="+new String("用户列表.xls".getBytes(),"ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			
			userService.exportExcel(userService.findObjects(),outputStream);
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	//导入用户列表数据
	public String importExcel(){
		if(userExcel != null){
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				userService.importExcel(userExcel,userExcelFileName);
			}
		}
		return "list";
	}
	//账户的唯一性校验
	public void verifyAccount(){
		try {
			if(user !=null && StringUtils.isNotBlank(user.getAccount())){
				//2、根据帐号到数据库中校验是否存在该帐号对应的用户
				List<User> list = userService.findUserByAccountAndId(user.getId(),user.getAccount());
				String strResult  =  "true";
				if(list != null && list.size()>0){
					strResult = "false";
				}
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	//上传头像相关
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public String getHeadImgContenType() {
		return headImgContenType;
	}
	public void setHeadImgContenType(String headImgContenType) {
		this.headImgContenType = headImgContenType;
	}
	//导入相关
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public String getUserExcelContenType() {
		return userExcelContenType;
	}
	public void setUserExcelContenType(String userExcelContenType) {
		this.userExcelContenType = userExcelContenType;
	}
	//
	
	
}
