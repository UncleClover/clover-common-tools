package com.clover.api.tools.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clover.api.tools.common.constants.ToolsConstant;

/**
 * 日期工具类
 * 
 * @author UncleClover
 * @time 2017年12月14日 下午2:59:54
 * @Email qiang900714@126.com
 */
public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 校验字符串字符串是否是正确的日期格式
	 * 
	 * @author UncleClover
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
	 * @author UncleClover
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
	 * @author UncleClover
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
	 * @author UncleClover
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
	 * @author UncleClover
	 * @time 2017年12月14日 下午5:23:54
	 * @Email qiang900714@126.com
	 * @return
	 */
	public static String random(int year) {
		return random(year, null);
	}

	/**
	 * 随机获取指定时间范围内当前日期或前或后的一个日期
	 * @author UncleClover
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

	/**
     * 获取当月第一天
     * 
     * @author UncleClover
     * @Email qiang900714@126.com
     * @time 2018年5月17日 上午11:17:49
     * @return
     */
	public static Timestamp getCurrnetMonthFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		return Timestamp.valueOf(format.format(calendar.getTime()));
	}
	
	/**
	 * 获取当月前一天最后时刻
	 * 
	 * @author UncleClover
	 * @Email qiang900714@126.com
	 * @time 2018年5月17日 上午11:17:59
	 * @return
	 */
	public static Timestamp getCurrnetMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, -1);
		return Timestamp.valueOf(format.format(calendar.getTime()));
	}
	
	/**
	 * 获取上月第一天
	 * 
	 * @author UncleClover
	 * @Email qiang900714@126.com
	 * @time 2018年5月17日 上午11:18:17
	 * @return
	 */
	public static Timestamp getLastMonthFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		return Timestamp.valueOf(format.format(calendar.getTime()));
	}

	/**
	 * 获取上月同期前一天最后时刻
	 * 
	 * @author UncleClover
	 * @Email qiang900714@126.com
	 * @time 2018年5月17日 上午11:17:34
	 * @return
	 */
	public static Timestamp getLastMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, -1);
		return Timestamp.valueOf(format.format(calendar.getTime()));
	}
	
	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = format.parse(beginDate);
			Date end = format.parse(endDate);

			if (start.getTime() >= end.getTime()) {
				return null;
			}

			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			System.out.println(format(randomDate("2018-04-01 00:00:00" ,"2018-04-30 23:59:59" )));
	}
}
