package com.clover.api.tools.common.constants;

import java.util.Random;

/**
 * 全国各省份名称和对应编码枚举类
 * 
 * @author zhangdq
 * @time 2017年12月13日 上午10:47:32
 * @Email qiang900714@126.com
 */
public enum ProvinceCode {
	bj("11", "北京市"), tj("12", "天津市"), he("13", "河北省"), sx("14", "山西省"), nm("15", "内蒙古自治区"), 
	ln("21", "辽宁省"), jl("22", "吉林省"), hlj("23", "黑龙江省"), sh("31", "上海市"), js("32", "江苏省"), 
	zj("33", "浙江省"), ah("34", "安徽省"), fj("35", "福建省"), jx("36", "江西省"), sd("37", "山东省"), 
	ha("41", "河南省"), hb("42", "湖北省"), hn("43", "湖南省"), gd("44", "广东省"), gx("45", "广西壮族自治区"), 
	hi("46", "海南省"), cq("50", "重庆市"), sc("51", "四川省"), gz("52", "贵州省"), yn("53", "云南省"), 
	xz("54", "西藏自治区"), sn("61", "陕西省"), gs("62", "甘肃省"), qh("63", "青海省"), nx("64", "宁夏回族自治区"), 
	xj("65", "新疆维吾尔自治区"), tw("71", "台湾省"), xg("81", "香港特别行政区"), am("82", "澳门特别行政区"), 
	nonentity("", "");

	private String name;
	private String code;

	ProvinceCode(String code, String name) {
		this.name = name;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * 根据code查询枚举信息
	 * 
	 * @author zhangdq
	 * @time 2017年12月13日 上午11:07:23
	 * @Email qiang900714@126.com
	 * @param code
	 * @return
	 */
	public static ProvinceCode getNameByCode(String code) {
		for (ProvinceCode provinceCode : ProvinceCode.values()) {
			if (provinceCode.getCode().equals(code)) {
				return provinceCode;
			}
		}
		return ProvinceCode.nonentity;
	}

	/**
	 * 根据名字查询枚举信息
	 * 
	 * @author zhangdq
	 * @time 2017年12月13日 上午11:07:50
	 * @Email qiang900714@126.com
	 * @param code
	 * @return
	 */
	public static ProvinceCode getCodeByName(String name) {
		for (ProvinceCode provinceCode : ProvinceCode.values()) {
			if (provinceCode.getName().equals(name)) {
				return provinceCode;
			}
		}
		return ProvinceCode.nonentity;
	}

	/**
	 * 获取枚举长度
	 * 
	 * @author zhangdq
	 * @time 2017年12月13日 下午2:22:31
	 * @Email qiang900714@126.com
	 * @return
	 */
	public static int length() {
		return ProvinceCode.values().length - 1;
	}

	/**
	 * 随机获取其中一个省份枚举信息
	 * 
	 * @author zhangdq
	 * @time 2017年12月13日 下午2:25:02
	 * @Email qiang900714@126.com
	 * @return
	 */
	public static ProvinceCode random() {
		return ProvinceCode.values()[new Random(System.currentTimeMillis()).nextInt(length())];
	}
}