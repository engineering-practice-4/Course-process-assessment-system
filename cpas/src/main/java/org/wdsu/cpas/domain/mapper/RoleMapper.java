package org.wdsu.cpas.domain.mapper;

import java.util.List;

import org.wdsu.cpas.domain.entity.Role;
import org.wdsu.cpas.domain.entity.RolePermission;


/**
 * 角色管理的DAO层
 * @author yuexiaofeng
 *
 */
public interface RoleMapper {
	void addRole(Role role);
	void deleteRole(Long roleId);
	Role findById(Long roleId);
	List<Role> findRolesByUserName(String userName);
	List<Role> findAllRoles();
	void updateRole(Role role);

	void deleteUserRole(Long roleId);
	void deleteRolePermission(Long roleId);
	void addRolePermission(RolePermission rolePermission);
}
