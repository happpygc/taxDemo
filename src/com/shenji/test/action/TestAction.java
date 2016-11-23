package com.shenji.test.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.shenji.test.service.TestService;

public class TestAction extends ActionSupport{

	@Resource
	private TestService testService;
	
	public String TestSS(){
		testService.say();
		return SUCCESS;
	}
}
