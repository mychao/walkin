package com.checkins.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * application context
 * @author mychao
 *
 */
public class MyApplicationContext implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MyApplicationContext.applicationContext = applicationContext;
	}

}
