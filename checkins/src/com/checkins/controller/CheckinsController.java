package com.checkins.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checkins.Constant;
import com.checkins.bean.CheckinsBean;
import com.checkins.pojo.CheckinsPojo;
import com.checkins.pojo.ResultPojo;
import com.checkins.service.CheckinsService;
import com.checkins.util.DateFormater;
import com.checkins.util.TypeUtils;
import com.google.gson.Gson;

/**
 * checkins controller
 * @author mychao
 *
 */
@RequestMapping(Constant.AUTHORITY_URI_BEGAIN+"checkins.do")
@Controller
public class CheckinsController {

	@Autowired
	private CheckinsService checkinsService;
	
	/**
	 * checkins list
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
		int total = checkinsService.statTotal();
		CheckinsPojo pojo = new CheckinsPojo();
		Gson gson = new Gson ();
		if(total > 0){
			List<CheckinsBean> list = checkinsService.queryForList(pageNow, pageSize);
			if(list != null){
				pojo.setRows(list);
			}
			pojo.setTotal(total);
		}
		String ret = gson.toJson(pojo);
		return ret;
	}
	/**
	 * add checkins info
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=save")
	public String save(HttpServletRequest req){
		int uid = TypeUtils.getIntParameterFromRequest("uid", req, 0);
		String geolocation = TypeUtils.getStringParameterFromRequest("geolocation", req, null);
		CheckinsBean bean = new CheckinsBean();
		bean.setUid(uid);
		bean.setCheckinTime(DateFormater.simpleDateFormat(new Date(), DateFormater.datetimeFormat));
		bean.setGeolocation(geolocation);
		int ret = checkinsService.add(bean);
		ResultPojo result = new ResultPojo();
		if(ret > 0){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
			result.setMsg("add new record failed!");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	/**
	 * edit checkins info
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=edit")
	public String edit(HttpServletRequest req){
		int id = TypeUtils.getIntParameterFromRequest("id", req, 0);
		int uid = TypeUtils.getIntParameterFromRequest("uid", req, 0);
		String geolocation = TypeUtils.getStringParameterFromRequest("geolocation", req, null);
		String checkinTime = TypeUtils.getStringParameterFromRequest("checkinTime", req, null);
		CheckinsBean bean = new CheckinsBean();
		bean.setId(id);
		bean.setUid(uid);
		bean.setCheckinTime(checkinTime);
		bean.setGeolocation(geolocation);
		int ret = checkinsService.edit(bean);
		ResultPojo result = new ResultPojo();
		if(ret > 0){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
			result.setMsg("edit record failed!");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	/**
	 * delete checkins
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=delete")
	public String delete(HttpServletRequest req){
		int id = TypeUtils.getIntParameterFromRequest("id", req, 0);
		int ret = checkinsService.delete(id);
		ResultPojo result = new ResultPojo();
		if(ret > 0){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
			result.setMsg("remove record failed!");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
}

