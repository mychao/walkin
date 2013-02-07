package com.checkins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.checkins.bean.AdministratorBean;

public interface AuthorityService{
	/**
	 * 指定后台cms根路径
	 */
	public static final String AUTHORITY_URI_BEGAIN = "/cms/manage/";
	/**
	 * 登录cms的后台管理员保存在session内的key
	 */
	public final static String LOGIN_MANAGER_INFO_SESSION_KEY = "managerInfo";
	
	/**
	 * 登录cms的后台管理员保存在cookie内的key
	 */
	public final static String LOGIN_MANAGER_INFO_COOKIE_KEY = LOGIN_MANAGER_INFO_SESSION_KEY;
	/**
	 * 登录后COOKIE的有效时间，秒
	 */
	public final static int LOGIN_MANAGER_INFO_COOKIE_AVALIABLE_TIME = 60 * 60 * 8;

	/**
	 * 登录用户信息放入session
	 * @param session
	 * @param manager
	 */
	public void setLoginManagerInfoToSession(HttpSession session, AdministratorBean manager);
	/**
	 * 从session中获得用户登录信息
	 * @param session
	 * @return
	 */
	public AdministratorBean getLoginManagerInfoFromSession(HttpSession session);
	
	/**
	 * 登录用户信息放入cookie
	 * @param res
	 * @param manager
	 */
	public void setLoginManagerInfoToCookie(HttpServletResponse res, AdministratorBean manager);
	/**
	 * 从cookie里获取用户登录信息
	 * @param req
	 * @return
	 */
	public AdministratorBean getLoginManagerInfoFromCookie(HttpServletRequest req);
	/**
	 * login by userName & password
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public int login(String userName, String passWord);
	/**
	 * 退出登录
	 * @param req
	 * @param res
	 * @return
	 */
	public int logout(HttpServletRequest req, HttpServletResponse res);
	
	/**
	 * 获取用户登录信息，取sesion，再取cookie
	 * @param req
	 * @param session
	 * @return
	 */
	public AdministratorBean getLoginManagerInfo(HttpServletRequest req, HttpSession session);
	
	/**
	 * 获取用户登录信息，取sesion，再取cookie,db为true时再查询数据库校验
	 * @param req
	 * @param session
	 * @param db 是否查询数据库
	 * @return
	 */
	public AdministratorBean getLoginManagerInfo(HttpServletRequest req, HttpSession session, boolean db);
}