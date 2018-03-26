package com.clover.api.tools.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clover.api.tools.common.constants.ToolsConstant;

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

	public static String format(Date date) {
		return format(date, null);
	}

	public static String format(Date date, String format) {
		if (StringUtil.isEmpty(format)) {
			format = "yyyyMMdd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
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

	/**
	 * 随机获取一个日期，默认是当前时间前后100年
	 * 
	 * @author zhangdq
	 * @time 2017年12月14日 下午5:23:54
	 * @Email qiang900714@126.com
	 * @return
	 */
	public static String random() {
		return random(ToolsConstant.DateParam.DEFAULT_YEAR, null);
	}

	/**
	 * 随机获取指定时间内的上下范围日期
	 * 
	 * @author zhangdq
	 * @time 2017年12月14日 下午5:23:54
	 * @Email qiang900714@126.com
	 * @return
	 */
	public static String random(int year) {
		return random(year, null);
	}

	/**
	 * 随机获取指定时间范围内当前日期或前或后的一个日期
	 * @author zhangdq
	 * @time 2017年12月14日 下午6:38:32
	 * @Email qiang900714@126.com
	 * @param year
	 * @param type
	 * @return
	 */
	public static String random(int year, String type) {
		if (StringUtil.isEmpty(type)) {
			Random rd = new Random();
			type = String.valueOf(rd.nextInt(2));
		}
		int date = Integer.parseInt(format(new Date(System.currentTimeMillis())));
		if (type.equals(ToolsConstant.DateParam.POS_DATE)) {
			date = date + new Random().nextInt(year * ToolsConstant.DateParam.YMD);
		} else {
			date = date - new Random().nextInt(year * ToolsConstant.DateParam.YMD);
		}
		return format(format(String.valueOf(date)));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			System.out.println(random());
	}
}
