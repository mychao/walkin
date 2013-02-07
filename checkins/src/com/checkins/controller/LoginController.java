package com.checkins.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checkins.Constant;
import com.checkins.bean.AdministratorBean;
import com.checkins.service.AuthorityService;
import com.checkins.util.TypeUtils;

/**
 * login controller
 * @author mychao
 *
 */
@RequestMapping(Constant.AUTHORITY_URI_BEGAIN + "login.do")
@Controller
public class LoginController {
	
	@Autowired
	private AuthorityService authorityService;

	/**
	 * 登录操作
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=login")
	public String login(HttpServletRequest req, HttpServletResponse res){
		String userName = TypeUtils.getStringParameterFromRequest("login", req, null);
		String passWord = TypeUtils.getStringParameterFromRequest("password", req, null);
		
		int ret = authorityService.login(userName, passWord);
		if(ret > 0){
			//login success
			AdministratorBean admin = new AdministratorBean();
			admin.setName(userName);
			admin.setId(ret);
			HttpSession session = req.getSession(true);
			authorityService.setLoginManagerInfoToSession(session, admin);
			authorityService.setLoginManagerInfoToCookie(res, admin);
			//return "redirect:"+Constant.AUTHORITY_URI_BEGAIN+"main.do?method=page";
		}else{
			//login failed
			//return ""+ret;
		}
		return ""+ret;
	}
	
}
