package org.wdsu.cpas.common.shiro.filter;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.wdsu.cpas.service.impl.UserServiceImpl;

/**
 * 扩展的FormAuthenticationFilter拦截器 
 * 主要用于登录成功后获取用户的权限信息，并将其存入session范围
 * @author yuexiaofeng
 *
 */
public class WithNavibarFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private UserServiceImpl userService;
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpReq=(HttpServletRequest)request;
		
		String userName=(String)SecurityUtils.getSubject().getPrincipal();
		List navigationBar=userService.getNavigationBar(userName);
		httpReq.getSession().setAttribute("navibar", navigationBar);
		return super.onLoginSuccess(token, subject, request, response);
	}

}
