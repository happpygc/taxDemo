package com.shenji.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shenji.core.dao.BaseDao;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements
		BaseDao<T> {
	
	Class<T> clazz;
	
	public BaseDaoImpl(){
		ParameterizedType a = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) a.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findObjectById(id));
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(entity);
	}

	@Override
	public T findObjectById(Serializable id) {
		// TODO Auto-generated method stub
		T t = getHibernateTemplate().get(clazz, id);
		
		return t;
	} 

	@Override
	public List<T> findObjects() {
		// TODO Auto-generated method stub
		
		Query query = getSession().createQuery("FROM "+clazz.getSimpleName());
		
		return query.list();
	}

}
