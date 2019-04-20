package org.wdsu.cpas.service;

import java.util.List;

import org.wdsu.cpas.domain.entity.Role;

/**
 * 角色管理的service层接口
 * @author Anonymous
 *
 */
public interface RoleService {
	Long addRole(Role role,Long...permissionIds);
	void deleteRole(Long roleId);
	void deleteMoreRoles(Long...roleIds);
	Role getRoleById(Long roleId);
	List<Role> getRolesByUserName(String userName);
	List<Role> getAllRoles();
	void updateRole(Role role,Long...permIds);
	
	void addRolePermissions(Long roleId,Long...permissionIds);
}
