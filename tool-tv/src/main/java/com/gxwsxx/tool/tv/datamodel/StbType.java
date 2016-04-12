package com.gxwsxx.tool.tv.datamodel;

/**
 * 机顶盒浏览器类型枚举类
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public enum StbType {

	ONE("通用电视机顶盒01", "0001", "sd", "480p", "CSM1800,富士通H60 Famos"), TWO("通用电视机顶盒02", "0002", "sd", "480P",
			"富士通H60,博通7019无卡,阿里3701G"), THREE("通用电视机顶盒03", "0003", "hd", "720p", "阿里3701H");

	private String name;// 名称
	private String key;// 键
	private String resolutionType;// 分辨率类型
	private String resolutionValue;// 分辨率值
	private String description;

	private StbType(String name, String key, String resolutionType, String resolutionValue, String description) {
		this.name = name;
		this.key = key;
		this.resolutionType = resolutionType;
		this.resolutionValue = resolutionValue;
		this.description = description;
	}

	/**
	 * 根据value值获取机顶盒类型枚举对象
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param value
	 *            值
	 * @return 机顶盒类型枚举对象
	 * @since 1.1
	 */
	public static StbType fromValue(String value) {
		StbType[] all = StbType.values();
		for (StbType t : all) {
			if (t.key.equals(value)) {
				return t;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}

	public String getResolutionType() {
		return resolutionType;
	}

	public String getResolutionValue() {
		return resolutionValue;
	}

	public String getDescription() {
		return description;
	}

}
