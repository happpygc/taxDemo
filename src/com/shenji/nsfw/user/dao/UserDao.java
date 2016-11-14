package com.shenji.nsfw.user.dao;

import java.util.List;

import com.shenji.core.dao.BaseDao;
import com.shenji.nsfw.user.entity.User;

public interface UserDao extends BaseDao<User>{

	List<User> findUserByAccountAndId(String id, String account);

}
