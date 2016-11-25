package com.shenji.test;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;











import com.shenji.test.entity.Person;
import com.shenji.test.service.TestService;

public class TestMerge {

	Log log = LogFactory.getLog(getClass());
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void loadCtx() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@Test
	public void testSpring() {
		TestService testService = (TestService) context.getBean("testService");
		testService.say();
	}
	@Test
	public void testHib() {
		SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(new Person("汪建军"));
		transaction.commit();
		session.close();
		
	}
	@Test
	public void testSerAndDao(){
		TestService testService = (TestService) context.getBean("testService");
		testService.save(new Person("汪建军4"));
	}
	@Test
	public void testLog(){
		
		log.debug("这是debug");
		log.info("这是info");
		log.warn("这是warning");
		log.error("这是error");
		log.fatal("这是fatal");
	}
	
}
