package org.wdsu.cpas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wdsu.cpas.domain.entity.User;
import org.wdsu.cpas.service.RoleService;
import org.wdsu.cpas.service.UserService;
import org.wdsu.cpas.service.PasswordService;


/**
 * 用户管理的控制层
 * @author yuexiaofeng
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordService passwordService;
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req){
		String error=null;
		String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        ModelAndView mav=new ModelAndView("login");
        mav.addObject("error", error);
		return mav;
	}
	
	@RequiresPermissions("user:list")//通过该注解实现权限访问控制
	@RequestMapping("/list")
	public ModelAndView showUserList(){
		List list=userService.getAllUsers();
		ModelAndView mav=new ModelAndView("user-list");
		mav.addObject("users", list);
		return mav;
	}
	
	@RequiresPermissions("user:add")
	@RequestMapping("/add")
	@ResponseBody
	public User addUser(User user,Long...roleIds){
		userService.addUser(user, roleIds);
		return user;
	}
	
	@RequiresPermissions("user:showroles")
	@RequestMapping("/showroles")
	@ResponseBody
	public List shwoRoles(String userName){
		return roleService.getRolesByUserName(userName);
	}

	@RequiresPermissions("role:list")
	@RequestMapping("/listRoles")
	@ResponseBody
	public List getRoles(){
		return roleService.getAllRoles();
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deleteUser(Long userId){
		userService.deleteUser(userId);
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMoreUsers(Long...userIds){
		userService.deleteMoreUsers(userIds);
	}
	
	@RequiresPermissions("user:corelationrole")
	@RequestMapping("/corelationRole")
	@ResponseBody
	public void corelationRole(Long userId,Long...roleIds){
		userService.updateUserRoles(userId, roleIds);
	}
}
