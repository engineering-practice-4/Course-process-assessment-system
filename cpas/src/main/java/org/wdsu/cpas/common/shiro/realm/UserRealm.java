package org.wdsu.cpas.common.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.wdsu.cpas.domain.entity.User;
import org.wdsu.cpas.service.UserService;


/**
 * 用户角色管理
 * @author yuexiaofeng
 *
 */
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;

	/**
	 * 获取用户角色信息和权限信息，以代后续进行访问控制 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findPermissionsByUserName(userName));//获取角色信息
		authorizationInfo.setStringPermissions(userService.findPermissionsByUserName(userName));//获取权限信息
		
		return authorizationInfo;
	}
	
	/**
	 * 获取用户凭证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();
		User user=userService.getUserByUserName(userName);//获取用户凭证(密码和密码盐)
		if(user==null){
			throw new UnknownAccountException();
		}
		//把密码查询出来，封装成SimpleAuthenticationInfo返回给Shiro进行验证
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
				user.getUserName(),
				user.getPassword(),
				ByteSource.Util.bytes(user.getUserName()+user.getPasswordSalt()),
				getName());
		return authenticationInfo;
	}

}
