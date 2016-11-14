package com.shenji.nsfw.user.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.shenji.core.dao.impl.BaseDaoImpl;
import com.shenji.nsfw.user.dao.UserDao;
import com.shenji.nsfw.user.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {

		String sSql = "FROM User WHERE account=?";
		if(StringUtils.isNotBlank(id)){
			sSql += "AND id !=?";
		}
		Query query = this.getSession().createQuery(sSql);
		query.setParameter(0, account);
		if(StringUtils.isNotBlank(id)){
			query.setParameter(1, id);
		}
		return query.list();
	}

}
