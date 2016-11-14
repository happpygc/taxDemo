package com.shenji.nsfw.role.service;

import java.util.List;

import com.shenji.nsfw.role.entity.Role;

public interface RoleService {

	public List<Role> findObjects();

	public void save(Role role);

	public Role findObjectById(String roleId);

	public void update(Role role);

	public void delete(String roleId);

}
