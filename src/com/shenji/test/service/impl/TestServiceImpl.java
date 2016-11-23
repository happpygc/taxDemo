package com.shenji.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shenji.test.Dao.TestDao;
import com.shenji.test.entity.Person;
import com.shenji.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Resource
	private TestDao testDao;
	
	@Override
	public void say() {
		System.err.println("我是service的实现方法");
	}

	@Override
	public void save(Person p) {
		testDao.save(p);
		//int i = 1/0;
	}
	
	
}
