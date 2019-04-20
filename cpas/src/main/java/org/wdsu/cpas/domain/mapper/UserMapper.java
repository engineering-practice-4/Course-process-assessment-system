package org.wdsu.cpas.domain.mapper;

import java.util.List;

import org.apache.shiro.web.tags.UserTag;
import org.wdsu.cpas.domain.entity.User;
import org.wdsu.cpas.domain.entity.UserRole;


/**
 * 用户管理的 Dao 层
 * @author yuexiaofeng
 *
 */
public interface UserMapper {
	

	void addUser(User user);
	void deleteUser(Long userId);
	User findUserByUserName(String userName);
	List<User> findAllUsers();
	
	void deleteUserRole(Long userId);
	void addUserRole(UserRole userRole);
	
	List<String> findRolesByUserName(String userName);
	List<String> findPermissionsByUserName(String userName);
}
