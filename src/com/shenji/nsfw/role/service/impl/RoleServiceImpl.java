package com.shenji.nsfw.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shenji.nsfw.role.dao.RoleDao;
import com.shenji.nsfw.role.entity.Role;
import com.shenji.nsfw.role.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleDao roleDao;

	@Override
	public List<Role> findObjects() {
		roleDao.findObjects();
		return null;
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public Role findObjectById(String roleId) {
		roleDao.findObjectById(roleId);
		return null;
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void delete(String roleId) {
		roleDao.delete(roleId);
	}

}
