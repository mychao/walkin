package com.checkins.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie常用方法定义
 * @author mychao
 *
 */
public class CookieUtils {

	public static int COOKIE_VALID_TIME = 100 * 24 * 60 * 1000;   // 100天
	
	/**
	 * 设置cookie
	 * @param resp
	 * @param cookieName 名称
	 * @param cookieValue 值
	 * @param seconds 有效期,秒
	 */
	public static void setCookie(HttpServletResponse resp, String cookieName, String cookieValue, int seconds){
		if(cookieValue==null){
			return;
		}
		
		Cookie cookie = new Cookie(cookieName ,cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(seconds);
		resp.addCookie(cookie);
	}
	
	/**
	 * 设置cookie
	 * @param resp
	 * @param cookieName 名称
	 * @param cookieValue 值
	 * @param seconds 有效期,秒
	 */
	public static void setCookie(HttpServletResponse resp, String cookieName, String cookieValue){
		setCookie(resp,cookieName,cookieValue,COOKIE_VALID_TIME);
	}	
	/**
	 * 取得cookie
	 * @param request
	 * @param cookieName 名称
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		if(cookieName==null){
			return null;
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie sCookie = cookies[i];
				if (cookieName.equals(sCookie.getName())) {
					String cValue = sCookie.getValue();
					return cValue == null ? null : cValue;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 移除cookie
	 * @param resp
	 * @param cookieName
	 */
	public static void removeCookie(HttpServletResponse resp, String cookieName){
		Cookie cookie = new Cookie(cookieName ,"");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
	}
	
}
