package com.shenji.nsfw.user.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.springframework.stereotype.Service;

import com.shenji.core.util.ExcelUtils;
import com.shenji.nsfw.user.dao.UserDao;
import com.shenji.nsfw.user.entity.User;
import com.shenji.nsfw.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public User findObjectById(Serializable id) {
		// TODO Auto-generated method stub
		return userDao.findObjectById(id);
		
	}

	@Override
	public List<User> findObjects() {
		// TODO Auto-generated method stub
		return userDao.findObjects();
	}
	//导出
	@Override
	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream) {
		ExcelUtils.exportUserExcel(userList, outputStream);
		
		
	}
	//导出
	@Override
	public void importExcel(File userExcel, String userExcelFileName) {
		ExcelUtils.importUserExcel(userDao, userExcel, userExcelFileName);
		
	}

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		return userDao.findUserByAccountAndId(id,account);
	}

}
