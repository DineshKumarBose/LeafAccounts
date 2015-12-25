package com.leafsoft.urlutilitis.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UrlUtilititisUtil {
	private static final Logger LOGGER = Logger.getLogger(UrlUtilititisUtil.class.getName());
	public static String enCodeUrl(String url) throws UnsupportedEncodingException {
		LOGGER.log(Level.INFO,"url:::::"+url);
		String enCodedUrl = URLEncoder.encode(url,"UTF-8");
		return enCodedUrl;
	}
	
	public static String deCodeUrl(String url) throws UnsupportedEncodingException {
		LOGGER.log(Level.INFO,"url:::::"+url);
		String deCodedUrl = URLDecoder.decode(url,"UTF-8");;
		return deCodedUrl;
	}
}
