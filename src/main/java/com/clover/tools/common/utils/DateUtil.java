package com.clover.tools.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * 
 * @author zhangdq
 * @time 2017年12月14日 下午2:59:54
 * @Email qiang900714@126.com
 */
public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 校验字符串字符串是否是正确的日期格式
	 * 
	 * @author zhangdq
	 * @time 2017年12月14日 下午3:22:57
	 * @Email qiang900714@126.com
	 * @param date
	 * @param format
	 * @return
	 */
	public static boolean isDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = format(date, format);
		if (d == null || date.equals(sdf.format(d))) {
			return true;
		}
		return false;
	}

	/**
	 * 格式日期-日期格式默认yyyyMMdd
	 * 
	 * @author zhangdq
	 * @time 2017年12月14日 下午3:27:46
	 * @Email qiang900714@126.com
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date format(String date) {
		return format(date, null);
	}
	
	/**
	 * 格式日期-日期格式默认yyyyMMdd
	 * 
	 * @author zhangdq
	 * @time 2017年12月14日 下午3:27:46
	 * @Email qiang900714@126.com
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date format(String date, String format) {
		if (StringUtil.isEmpty(format)) {
			format = "yyyyMMdd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			logger.error("日期{}格式化异常", date);
		}
		return d;
	}

	public static void main(String[] args) {
		System.out.println(isDate("2017q11111", "yyyyMMdd"));
	}
}
