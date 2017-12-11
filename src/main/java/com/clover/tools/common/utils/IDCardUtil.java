package com.clover.tools.common.utils;

/**
 * 身份证工具类
 * 
 * @author zhangdq
 * @time 2017年12月8日 上午11:13:04
 * @Email qiang900714@126.com
 */
public class IDCardUtil {
	public static boolean isIdCardNo(String idCard) {
		if (StringUtil.isEmpty(idCard)) {
			return false;
		}
		
		// 一代身份证有15位身份证存在-年份只有后两位且没有最后一位校验位
		if (idCard.length() != 18 || idCard.length() != 15) {
			return false;
		}
		return true;
	}
}
