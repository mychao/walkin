package com.checkins;
/**
 * 常量定义
 * @author mychao
 *
 */
public class Constant {

	/**
	 * authority uri define
	 */
	public static final String AUTHORITY_URI_BEGAIN = "/checkins/";
	
	/**
	 * template base path
	 */
	public static final String TEMPLATES_BASE_URI = "/templates/";
	
	/**
	 * get template file path
	 * @param templateFilePath
	 * @return
	 */
	public static String getTemplateFilePath(String templateFilePath){
		return TEMPLATES_BASE_URI + templateFilePath;
	}
}
