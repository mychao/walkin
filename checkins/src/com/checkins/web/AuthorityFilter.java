package com.checkins.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.checkins.Constant;
import com.checkins.bean.AdministratorBean;
import com.checkins.service.AuthorityService;
import com.checkins.util.MyApplicationContext;
import com.checkins.util.TypeUtils;

/**
 * 权限认证过滤器
 * @author mychao
 *
 */
public class AuthorityFilter implements javax.servlet.Filter{

	/**
	 * 无需过滤的路径
	 */
	private static String[] noFilterPaths;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)servletRequest;
		HttpServletResponse res = (HttpServletResponse)servletResponse;
		
		String requestUri = req.getRequestURI();
		System.out.println("requestUri: " + requestUri);
		if(!isNoNeedFilterPath(requestUri)){
			if(requestUri.indexOf(Constant.AUTHORITY_URI_BEGAIN) != -1){
				AuthorityService authorityService = (AuthorityService) MyApplicationContext.getApplicationContext().getBean("authorityService");
				AdministratorBean managerInfo = authorityService.getLoginManagerInfoFromSession(req.getSession());
				if(managerInfo == null){
					managerInfo = authorityService.getLoginManagerInfoFromCookie(req);
					if(managerInfo == null){
						res.sendRedirect("/cms/login.jsp");
						return;
					}
					authorityService.setLoginManagerInfoToSession(req.getSession(), managerInfo);
				}
			}
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		String noFilterPaths = filterConfig.getInitParameter("noAuthorityPath");
		if(TypeUtils.isEmptyString(noFilterPaths)){
			return;
		}
		AuthorityFilter.noFilterPaths = noFilterPaths.split(",");
	}

	/**
	 * 是否不需要过滤
	 * @param requestUri
	 * @return
	 */
	private boolean isNoNeedFilterPath (String requestUri){
		if(noFilterPaths == null || noFilterPaths.length <= 0){
			return false;
		}
		for(String path: noFilterPaths){
			if(TypeUtils.isEmptyString(path)){
				continue;
			}
			if(requestUri.indexOf(path) != -1){
				return true;
			}
		}
		return false;
	}
	
}
