package com.clover.tools.common.constants;

/**
 * 全国各省份名称和对应编码枚举类
 * 
 * @author zhangdq
 * @time 2017年12月13日 上午10:47:32
 * @Email qiang900714@126.com
 */
public enum ProvinceCode {
	bj("11", "北京"), tj("12", "天津"), he("13", "河北"), sx("14", "山西"), nm("15", "内蒙古"), ln("21", "辽宁"), 
	jl("22", "吉林"), hlj("23", "黑龙江"), sh("31", "上海"), js("32", "江苏"), zj("33", "浙江"), ah("34", "安徽"), 
	fj("35", "福建"), jx("36", "江西"), sd("37", "山东"), ha("41", "河南"), hb("42", "湖北"), hn("43", "湖南"), 
	gd("44", "广东"), gx("45", "广西"), hi("46", "海南"), cq("50", "重庆"), sc("51", "四川"), gz("52", "贵州"), 
	yn("53", "云南"), xz("54", "西藏"), sn("61", "陕西"), gs("62", "甘肃"), qh("63", "青海"), nx("64", "宁夏"), 
	xj("65", "新疆"), nonentity("", "");

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
}