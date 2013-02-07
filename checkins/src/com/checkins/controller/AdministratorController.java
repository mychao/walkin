package com.checkins.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checkins.Constant;
import com.checkins.bean.AdministratorBean;
import com.checkins.pojo.CheckinsPojo;
import com.checkins.service.AdministratorService;
import com.checkins.util.TypeUtils;
import com.google.gson.Gson;

/**
 * admin controller
 * @author mychao
 *
 */
@RequestMapping(Constant.AUTHORITY_URI_BEGAIN+"admin.do")
@Controller
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * administrator list
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=list")
	public String list(HttpServletRequest req){
		//当前页
		int pageNow = TypeUtils.getIntParameterFromRequest("page", req, 1);
		//每页显示条数
		int pageSize = TypeUtils.getIntParameterFromRequest("rows", req, 10);
		int total = administratorService.statTotal();
		CheckinsPojo pojo = new CheckinsPojo();
		Gson gson = new Gson ();
		if(total > 0){
			List<AdministratorBean> list = administratorService.query(pageNow, pageSize);
			if(list != null){
				pojo.setRows(list);
			}
			pojo.setTotal(total);
		}
		String ret = gson.toJson(pojo);
		return ret;
	}
	/**
	 * add administrator info
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=save")
	public String save(HttpServletRequest req){
		String userName = TypeUtils.getStringParameterFromRequest("name", req, null);
		String password = TypeUtils.getStringParameterFromRequest("password", req, null);
		AdministratorBean bean = new AdministratorBean();
		bean.setName(userName);
		bean.setPassword(password);
		int ret = administratorService.addNewAdministrator(bean);
		return ret+"";
	}
	/**
	 * edit administrator info
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=edit")
	public String edit(HttpServletRequest req){
		int id = TypeUtils.getIntParameterFromRequest("id", req, 0);
		String userName = TypeUtils.getStringParameterFromRequest("name", req, null);
		String password = TypeUtils.getStringParameterFromRequest("password", req, null);
		AdministratorBean bean = new AdministratorBean();
		bean.setId(id);
		bean.setName(userName);
		bean.setPassword(password);
		int ret = administratorService.edit(bean);
		return ret+"";
	}
	
	/**
	 * delete administrator
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=delete")
	public String delete(HttpServletRequest req){
		int id = TypeUtils.getIntParameterFromRequest("id", req, 0);
		int ret = administratorService.delete(id);
		return ret + "";
	}
}
