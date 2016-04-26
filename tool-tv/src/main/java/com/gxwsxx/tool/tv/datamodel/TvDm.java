package com.gxwsxx.tool.tv.datamodel;

import com.gxwsxx.tool.tv.annotation.TvUserParameter;

import java.io.Serializable;

/**
 * 电视用户访问相关参数<br>
 *
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class TvDm implements Serializable {
    private static final long serialVersionUID = -5977138155006302596L;

    // 机顶盒编号
    @TvUserParameter(name = {"stb", "device_id"}, description = "机顶盒编号") private String stb;

    // idc用户编号
    @TvUserParameter(name = {"idc", "user_id"}, description = "用户编号") private String idc;

    // 区域编号
    @TvUserParameter(name = {"area", "area_code"}, description = "区域码") private String area;

    // 机顶盒类型
    @TvUserParameter(name = {"type", "stbType"}, description = "机顶盒类型") private String type;

    private String time;

    // url参数
    private String params;

    //广电格式url参数
    private String paramsgd;

    public String getStb() {
        return stb;
    }

    public void setStb(String stb) {
        this.stb = stb;
    }

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getParamsgd() {
        return paramsgd;
    }

    public void setParamsgd(String paramsgd) {
        this.paramsgd = paramsgd;
    }
}
