package com.shenji.test.Dao.Impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shenji.test.Dao.TestDao;
import com.shenji.test.entity.Person;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

	@Override
	public void save(Person p) {
		getHibernateTemplate().save(p);
		
	}

}
