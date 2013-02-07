package com.checkins.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checkins.Constant;
import com.checkins.bean.PlaceBean;
import com.checkins.pojo.CheckinsPojo;
import com.checkins.pojo.ResultPojo;
import com.checkins.service.PlaceService;
import com.checkins.util.TypeUtils;
import com.google.gson.Gson;
/**
 * place controller
 * @author mychao
 *
 */
@RequestMapping(Constant.AUTHORITY_URI_BEGAIN + "place.do")
@Controller
public class PlaceController {
	@Autowired
	private PlaceService placeService;
	/**
	 * places list
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
		int total = placeService.statTotal();
		CheckinsPojo pojo = new CheckinsPojo();
		Gson gson = new Gson ();
		if(total > 0){
			List<PlaceBean> list = placeService.queryForList(pageNow, pageSize);
			if(list != null){
				pojo.setRows(list);
			}
			pojo.setTotal(total);
		}
		String ret = gson.toJson(pojo);
		return ret;
	}
	/**
	 * add places info
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=save")
	public String save(HttpServletRequest req){
		String geolocation = TypeUtils.getStringParameterFromRequest("geolocation", req, null);
		String address = TypeUtils.getStringParameterFromRequest("address", req, null);
		PlaceBean bean = new PlaceBean();
		bean.setGeolocation(geolocation);
		bean.setAddress(address);
		int ret = placeService.add(bean);
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
	 * edit places info
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=edit")
	public String edit(HttpServletRequest req){
		int id = TypeUtils.getIntParameterFromRequest("id", req, 0);
		String geolocation = TypeUtils.getStringParameterFromRequest("geolocation", req, null);
		String address = TypeUtils.getStringParameterFromRequest("address", req, null);
		PlaceBean bean = new PlaceBean();
		bean.setId(id);
		bean.setGeolocation(geolocation);
		bean.setAddress(address);
		int ret = placeService.edit(bean);
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
	 * delete places
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "method=delete")
	public String delete(HttpServletRequest req){
		int id = TypeUtils.getIntParameterFromRequest("id", req, 0);
		int ret = placeService.delete(id);
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
