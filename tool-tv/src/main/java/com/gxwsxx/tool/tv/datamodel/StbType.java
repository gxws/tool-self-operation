package com.gxwsxx.tool.tv.datamodel;

/**
 * 机顶盒浏览器类型枚举类
 *
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public enum StbType {

    ONE("通用电视机顶盒01", "0001", "sd", "480p", "CSM1800,富士通H60 Famos"), TWO("通用电视机顶盒02", "0002", "sd",
        "480P", "富士通H60,博通7019无卡,阿里3701G"), THREE("通用电视机顶盒03", "0003", "hd", "720p", "阿里3701H");

    private String name;// 名称
    private String key;// 键
    private String type;// 分辨率类型
    private String value;// 分辨率值
    private String description;

    StbType(String name, String key, String type, String value, String description) {
        this.name = name;
        this.key = key;
        this.type = type;
        this.value = value;
        this.description = description;
    }

    /**
     * 根据value值获取机顶盒类型枚举对象
     *
     * @param value 值
     * @return 机顶盒类型对象
     * @since 1.1
     */
    public static StbType fromInt(int value) {
        return values()[value + 1];
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getResolutionType() {
        return type;
    }

    public String getResolutionValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
