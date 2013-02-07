package com.checkins.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkins.bean.AdministratorBean;
import com.checkins.dao.AdministratorDao;
import com.checkins.service.AuthorityService;
import com.checkins.util.CookieUtils;
import com.checkins.util.TypeUtils;

public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AdministratorDao authorityDao;

	@Override
	public AdministratorBean getLoginManagerInfoFromSession(HttpSession session) {
		// TODO Auto-generated method stub
		if(session == null){
			return null;
		}
		String managerInfo = (String) session.getAttribute(LOGIN_MANAGER_INFO_SESSION_KEY);
		return splitStringToManagerBean(managerInfo);
	}
	
	/**
	 * 分割字符串设置管理员信息值,并通过数据库查询校验
	 * @param managerInfo
	 * @return
	 */
	private AdministratorBean splitStringToManagerBean(String managerInfo){
		if(TypeUtils.isEmptyString(managerInfo)){
			return null;
		}
		String[] managerInfoArray = managerInfo.split("_");
		AdministratorBean manager = new AdministratorBean();
		try{
			manager.setId(Integer.parseInt(managerInfoArray[0]));
			manager.setName(managerInfoArray[1]);
		}catch(Exception e){
			return null;
		}
		return manager;
	}

	@Override
	public void setLoginManagerInfoToSession(HttpSession session,
			AdministratorBean manager) {
		// TODO Auto-generated method stub
		if(session == null || manager == null){
			return;
		}
		String managerInfo = manager.getId() + "_" + manager.getName();
		session.setAttribute(LOGIN_MANAGER_INFO_SESSION_KEY, managerInfo);
	}

	@Override
	public void setLoginManagerInfoToCookie(HttpServletResponse res,
			AdministratorBean manager) {
		// TODO Auto-generated method stub
		if(res == null || manager == null){
			return;
		}
		String managerInfo = manager.getId() + "_" + manager.getName();
		CookieUtils.setCookie(res, LOGIN_MANAGER_INFO_COOKIE_KEY, managerInfo, LOGIN_MANAGER_INFO_COOKIE_AVALIABLE_TIME);
	}

	@Override
	public AdministratorBean getLoginManagerInfoFromCookie(HttpServletRequest req) {
		String managerInfo = CookieUtils.getCookie(req, LOGIN_MANAGER_INFO_COOKIE_KEY);
		
		return this.splitStringToManagerBean(managerInfo);
	}

	@Override
	public int logout(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		req.getSession(true).removeAttribute(LOGIN_MANAGER_INFO_SESSION_KEY);
		CookieUtils.removeCookie(res, LOGIN_MANAGER_INFO_COOKIE_KEY);
		return 0;
	}

	@Override
	public AdministratorBean getLoginManagerInfo(HttpServletRequest req,
			HttpSession session) {
		AdministratorBean manager = getLoginManagerInfoFromSession(session);
		if(manager != null){
			return manager;
		}
		manager = getLoginManagerInfoFromCookie(req);
		return manager;
	}

	@Override
	public AdministratorBean getLoginManagerInfo(HttpServletRequest req,
			HttpSession session, boolean db) {
		AdministratorBean manager = getLoginManagerInfo(req, session);
		if(db && manager != null){
			manager = authorityDao.queryById(manager.getId());
		}
		return manager;
	}

	@Override
	public int login(String userName, String passWord) {
		AdministratorBean admin = authorityDao.queryById(userName);
		if(admin == null){
			//user not exists
			return -1;
		}
		if(!admin.getPassword().equals(passWord)){
			//error password
			return -2;
		}else{
			//success
			return admin.getId();
		}
	}
}