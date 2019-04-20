package org.wdsu.cpas.domain.mapper;

import java.util.List;

import org.wdsu.cpas.domain.entity.Permission;


/**
 * 权限管理的 DAO 层
 * @author yuexiaofeng
 *
 */
public interface PermissionMapper {
	void addPermission(Permission permission);
	void deletePermission(Long permissionId);
	Permission findById(Long permId);
	List<Permission> findNavisByRoleId(Long roleId);
	List<Permission> findPermissionsByRoleId(Long roleId);
	List<Permission> findAllPermissions();
	void updatePermission(Permission permission);
	
	void deleteRolePermission(Long permissionId);
}
