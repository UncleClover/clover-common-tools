package com.clover.api.tools.common.utils;

import java.util.Random;
import com.clover.api.tools.common.constants.ProvinceCode;
import com.clover.api.tools.common.constants.ToolsConstant;

/**
 * 身份证工具类
 * 
 * @author UncleClover
 * @time 2017年12月8日 上午11:13:04
 * @Email qiang900714@126.com
 */
public class IDCardUtil {
	public static boolean isIdCardNo(String idCard) {
		if (StringUtil.isEmpty(idCard)) {
			return false;
		}

		// 一代身份证有15位身份证存在-年份只有后两位且没有最后一位校验位
		if (idCard.length() != 18 && idCard.length() != 15) {
			return false;
		}

		// 18位身份证前17位一定全是数字，最后一位可能是X
		if (idCard.length() == 18 && !StringUtil.isNumberic(idCard.substring(0, 17))) {
			return false;
		}

		// 15位身份证全是数字
		if (idCard.length() == 15 && !StringUtil.isNumberic(idCard)) {
			return false;
		}

		// 前两位省市区
		String topTwo = idCard.substring(0, 2);
		ProvinceCode provinceCode = ProvinceCode.getNameByCode(topTwo);
		if (StringUtil.isEmpty(provinceCode.getName())) {
			return false;
		}

		/**
		 * 第3、4位数字表示：所在城市的代码 第5、6位数字表示：所在区县的代码
		 * 3-6位数字代表的地市区县已经发生过多次变更，已无法实现严格意义的校验
		 */

		// 出生年月
		String birthday = "";
		if (idCard.length() == 18) {
			birthday = idCard.substring(6, 14);
		}
		if (idCard.length() == 15) {
			// 15位身份证出生年份没有前两位，自动不补齐
			birthday = "19" + idCard.substring(6, 12);
		}
		if (!DateUtil.isDate(birthday, "yyyyMMdd")) {
			return false;
		}

		// 15位身份证到此就校验通过
		if (idCard.length() == 15) {
			return true;
		}

		// 获取18位
		String verifyCode = idCard.substring(17);
		if (verifyCode.equalsIgnoreCase(calculateVerifyCode(idCard))) {
			return true;
		}
		return false;
	}

	/**
	 * 随机获取一个身份证
	 * 
	 * @author UncleClover
	 * @time 2017年12月14日 下午5:13:29
	 * @Email qiang900714@126.com
	 * @return
	 */
	public static String random() {
		StringBuffer sb = new StringBuffer();
		sb.append(ProvinceCode.random().getCode());
		sb.append(String.valueOf(new Random().nextInt(9000) + 1000));
		sb.append(DateUtil.random(ToolsConstant.DateParam.DEFAULT_YEAR, ToolsConstant.DateParam.PRE_DATE));
		sb.append(String.valueOf(new Random().nextInt(900) + 100));
		sb.append(calculateVerifyCode(sb.toString()));
		return sb.toString();
	}
	/**
	 * 根据地址码、生辰随机生成一个身份证
	 * 
	 * @author UncleClover
	 * @time 2017年12月14日 下午9:55:54
	 * @Email qiang900714@126.com
	 * @param addressCode
	 * @param birthday
	 * @return
	 */
	public static String random(String addressCode, String birthday) {
		return random(addressCode, birthday, null); 
	}

	/**
	 * 根据地址码、生辰和性别随机生成一个身份证
	 * 
	 * @author UncleClover
	 * @time 2017年12月14日 下午9:28:50
	 * @Email qiang900714@126.com
	 * @param addressCode
	 * @return
	 */
	public static String random(String addressCode, String birthday, String sex) {
		if (StringUtil.isEmpty(addressCode) || addressCode.length() != 6) {
			addressCode = ProvinceCode.random().getCode() + (new Random().nextInt(9000) + 1000);
		}
		if (StringUtil.isEmpty(birthday) || birthday.length() != 6) {
			birthday = DateUtil.random(ToolsConstant.DateParam.DEFAULT_YEAR, ToolsConstant.DateParam.PRE_DATE);
		}
		if(StringUtil.isEmpty(sex) || (!sex.equals(ToolsConstant.IdCardParam.MAN) && !sex.equals(ToolsConstant.IdCardParam.WOMEN))){
			sex = String.valueOf(new Random().nextInt(900) + 100);
		}else{
			sex = String.valueOf(new Random().nextInt(90) + 10) + String.valueOf(new Random().nextInt(20)/2);
		}
		StringBuffer sb = new StringBuffer();
		sb.append(addressCode).append(birthday).append(sex);
		return sb.append(calculateVerifyCode(sb.toString())).toString(); 
	}

	/**
	 * 计算校验码
	 * 
	 * @author UncleClover
	 * @time 2017年12月14日 下午3:52:48
	 * @Email qiang900714@126.com
	 * @param idCard
	 * @return
	 */
	private static String calculateVerifyCode(String idCard) {
		int sum = 0;

		// sum除以11的余数依次和以下数组字符对应
		String[] verifyCodes = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

		// 身份证前17位依次和以下数字相乘后相加
		int[] coefficient = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

		char[] c = idCard.substring(0, 17).toCharArray();
		for (int i = 0; i < c.length; i++) {
			sum += Integer.parseInt(String.valueOf(c[i])) * coefficient[i];
		}
		return verifyCodes[sum % 11];
	}
}
