package com.clover.tools.common.utils;

/**
 * 字符串工具类
 * 
 * @author zhangdq
 * @time 2017年12月8日 上午10:57:24
 * @Email qiang900714@126.com
 */
public class StringUtil {
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * 字符串是否有空格
	 * @author zhangdq
	 * @time 2017年12月8日 上午11:05:40
	 * @Email qiang900714@126.com
	 * @param str
	 * @return
	 */
	public static boolean hasBlank(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		
		if(str.length() != str.trim().length()){
			return true;
		}
		
		return false;
	}
	
	public static boolean hasNoBlank(String str){
		return !hasBlank(str);
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtil.hasNoBlank(" a  "));
	}
}
