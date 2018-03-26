package com.clover.api.tools.common.constants;

/**
 * 常量类
 * 
 * @author zhangdq
 * @time 2017年12月14日 下午9:36:09
 * @Email qiang900714@126.com
 */
public class ToolsConstant {
	private ToolsConstant() {
	}

	/**
	 * 日期类常量
	 * 
	 * @author zhangdq
	 * @time 2017年12月14日 下午9:36:28
	 * @Email qiang900714@126.com
	 */
	public final static class DateParam {
		private DateParam() {

		}

		/**
		 * 当前时间前后100年
		 */
		public final static int DEFAULT_YEAR = 100;

		/**
		 * 系数
		 */
		public final static int YMD = 10000;

		/**
		 * 当前时间之前
		 */
		public final static String PRE_DATE = "pre";

		/**
		 * 当前时间之后
		 */
		public final static String POS_DATE = "pos";
	}

	/**
	 * 身份证常量
	 * 
	 * @author zhangdq
	 * @time 2017年12月14日 下午9:38:18
	 * @Email qiang900714@126.com
	 */
	public final static class IdCardParam {
		private IdCardParam() {

		}
		
		/**
		 * 女性
		 */
		public final static String WOMEN = "EVEN";
		
		/**
		 * 男性
		 */
		public final static String MAN = "ODD";
	}
}
