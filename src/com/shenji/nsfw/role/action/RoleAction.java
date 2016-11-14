package com.shenji.nsfw.role.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.shenji.core.action.BaseAction;
import com.shenji.core.constant.Constant;
import com.shenji.nsfw.role.entity.Role;
import com.shenji.nsfw.role.service.RoleService;

public class RoleAction extends BaseAction{
	
	@Resource
	private RoleService roleService;

	private List<Role> roleList;
	private Role role;
	//列表页面
		public String listUI(){
			 roleList = roleService.findObjects();
			 return "listUI";
		}
		//跳转到新增页面
		public String addUI(){
			//加载权限集合
			ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
			
			return "addUI";
		}
		//保存新增
		public String add(){
			try {
				if(role!=null){
					
					roleService.save(role);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//跳转到编辑页面
		public String editUI(){
			if (role != null && role.getRoleId() != null) {
				role = roleService.findObjectById(role.getRoleId());
			}
			return "editUI";
		}
		//保存编辑
		public String edit(){
			try {
				if(role!=null){
					roleService.update(role);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//删除
		public String delete(){
			if(role != null && role.getRoleId() != null){
				roleService.delete(role.getRoleId());
			}
			return "list";
		}
		//批量删除
		public String deleteSelected(){
			if(selectedRow != null){
				for(String id: selectedRow){
					roleService.delete(id);
				}
			}
			return "list";
		}
		
		
}
