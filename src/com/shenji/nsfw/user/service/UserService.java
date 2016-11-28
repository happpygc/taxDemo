package com.shenji.nsfw.user.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.shenji.nsfw.user.entity.User;

public interface UserService {

	//新增
	public void save(User user);
	//更新
	public void update(User user);
	//根据id删除O
	public void delete(Serializable id);
	//根据id查找
	public User findObjectById(Serializable id);
	//查找列表
	public List<User> findObjects();
	//导出
	public void exportExcel(List<User> findObjects,
			ServletOutputStream outputStream);
}
