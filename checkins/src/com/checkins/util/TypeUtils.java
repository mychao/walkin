package com.checkins.util;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

/**
 * 类型常用方法类
 * 
 * @author mychao
 * 
 */
public class TypeUtils {
	/**
	 * 字符串是否为空判断
	 * 
	 * @param str
	 * @return true:为空;false:不为空
	 */
	public static boolean isEmptyString(String str) {
		if (str == null || str.isEmpty() || "null".equals(str)) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * 去除首位空格
	 * @param source
	 * @return
	 */
	public static String trim(Object source){
		if(source == null){
			return null;
		}
		String ret = source.toString();
		return ret.trim();
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param numStr
	 * @return
	 */
	public static boolean isNumber(String numStr) {
		if (isEmptyString(numStr)) {
			return false;
		}
		char[] chs = numStr.toCharArray();
		for (char ch : chs) {
			if (ch > '9' || ch < '0') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 从request中取出参数值：字符串
	 * 
	 * @param paramName
	 *            参数名
	 * @param req
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static String getStringParameterFromRequest(String paramName,
			HttpServletRequest req, String defaultValue) {
		if (TypeUtils.isEmptyString(paramName)) {
			return defaultValue;
		}
		String value = req.getParameter(paramName);
		if (TypeUtils.isEmptyString(value)) {
			return defaultValue;
		}
		return value;
	}

	
	/**
	 * 从request中取出参数值：Double
	 * 
	 * @param paramName
	 *            参数名
	 * @param req
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static Double getDoubleParameterFromRequest(String paramName,
			HttpServletRequest req, double defaultValue) {
		if (TypeUtils.isEmptyString(paramName)) {
			return defaultValue;
		}
		String value = req.getParameter(paramName);
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
		}
		return defaultValue;
	}
	/**
	 * 从request中取出整形参数值
	 * 
	 * @param paramName
	 *            参数名
	 * @param req
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static int getIntParameterFromRequest(String paramName,
			HttpServletRequest req, int defaultValue) {
		if (TypeUtils.isEmptyString(paramName)) {
			return defaultValue;
		}
		String value = req.getParameter(paramName);
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * 字符编码转换
	 * 
	 * @param orgCharSet
	 *            转化前的编码
	 * @param aimCharSet
	 *            转化后的编码
	 * @param content
	 *            内容
	 * @return
	 */
	public static String charSetChange(String orgCharSet, String aimCharSet,
			String content) {
		if (TypeUtils.isEmptyString(content)) {
			return content;
		}
		try {
			content = new String(content.getBytes(orgCharSet), aimCharSet);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 字符串数组转化为整形数组
	 * 
	 * @param strArray
	 * @return
	 */
	public static Integer[] strArrayToIntArray(String[] strArray) {
		if (strArray == null) {
			return null;
		}
		int length = strArray.length;
		Integer[] ret = new Integer[length];
		for (int i = 0; i < length; i++) {
			try {
				ret[i] = Integer.parseInt(strArray[i]);
			} catch (Exception e) {
			}
		}
		return ret;
	}

}
