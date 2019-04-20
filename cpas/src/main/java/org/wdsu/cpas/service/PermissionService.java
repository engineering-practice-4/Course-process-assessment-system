package org.wdsu.cpas.service;

import java.util.List;

import org.wdsu.cpas.domain.entity.Permission;

/**
 * 权限管理的 service层接口
 * @author yuexiaofeng
 *
 */
public interface PermissionService {
	Long addPermission(Permission permission);
	void deletePermission(Long permissionId);
	void deleteMorePermissions(Long...permIds);
	Permission findById(Long permId);
	List<Permission> getPermissionsByRoleId(Long roleId);
	List<Permission> getAllPermissions();
	void updatePermission(Permission permission);
}
