package com.gxwsxx.tool.web.tv.data;

import java.io.Serializable;

import com.gxwsxx.tool.web.tv.annotation.WebTvUserParameter;

/**
 * 电视用户访问相关参数<br>
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvUserParam implements Serializable {
	private static final long serialVersionUID = -5977138155006302596L;

	public static final String ATTRIBUTE_NAME = "webTvUserParam";

	// 机顶盒编号
	@WebTvUserParameter(name = { "stb", "device_id" }, description = "机顶盒编号")
	private String stb;

	// idc用户编号
	@WebTvUserParameter(name = { "idc", "user_id" }, description = "用户编号")
	private String idc;

	// 区域编号
	@WebTvUserParameter(name = { "area", "area_code" }, description = "区域码")
	private String area;

	// 机顶盒类型
	@WebTvUserParameter(name = { "type" }, description = "机顶盒类型")
	private String type;

	// url格式参数
	private String urlparam;

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

	public String getUrlparam() {
		return urlparam;
	}

	public void setUrlparam(String urlparam) {
		this.urlparam = urlparam;
	}

}
