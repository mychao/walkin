package com.checkins.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checkins.Constant;

/**
 * main frame controller
 * @author mychao
 *
 */
@RequestMapping(Constant.AUTHORITY_URI_BEGAIN+"main.do")
@Controller
public class MainController {

	/**
	 * main page
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "method=page")
	public String main(HttpServletRequest req){
		return Constant.getTemplateFilePath("main/main.jsp");
	}
	
}
