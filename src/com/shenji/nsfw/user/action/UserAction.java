package com.shenji.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;







import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shenji.nsfw.user.entity.User;
import com.shenji.nsfw.user.service.UserService;

public class UserAction extends ActionSupport {
	
	@Resource
	private UserService userService;
	
	
/*	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}*/
	private List<User> userList;
	private User user;
	private String[] selectedRow;
	//上传头像相关
	private File headImg;
	private String headImgFileName;
	private String headImgContenType;
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
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
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
	
	
	
}
