package org.wdsu.cpas.service;

import java.util.List;
import java.util.Set;

import org.wdsu.cpas.domain.entity.Navigation;
import org.wdsu.cpas.domain.entity.User;


/**
 * 用户管理的service层接口
 * @author yuexiaofeng
 *
 */
public interface UserService {
	void addUser(User user,Long...roleIds);
	void deleteUser(Long userId);
	void deleteMoreUsers(Long...userIds);
	User getUserByUserName(String userName);
	List<User> getAllUsers();
	
	void updateUserRoles(Long userId,Long...roleIds);
	
	Set<String> findRolesByUserName(String userName);
	Set<String> findPermissionsByUserName(String userName);
	
	List<Navigation> getNavigationBar(String userName);
}
