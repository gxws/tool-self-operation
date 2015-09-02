package com.gxws.tool.web.tv.data;

import java.io.Serializable;

import com.gxws.tool.web.tv.annotation.WebTvUserParameter;

/**
 * 电视用户访问相关参数<br>
 * 
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvUserParam implements Serializable {
	private static final long serialVersionUID = -5977138155006302596L;

	public static final String ATTR_NAME = "webTvUser";

	// 机顶盒编号
	@WebTvUserParameter(name = { "stbId", "device_id" }, description = "机顶盒编号")
	private String stbId;

	// dvb用户编号
	@WebTvUserParameter(name = { "dvbId", "user_id" }, description = "用户编号")
	private String dvbId;

	// 区域编号
	@WebTvUserParameter(name = { "areaId", "area_code" }, description = "区域码")
	private String areaId;

	// 机顶盒类型
	@WebTvUserParameter(name = { "stbType", "device_type" }, description = "机顶盒类型")
	private String stbType;

	// url参数，格式 &stbId=123&dvbId=456&areaId=789&stbType=0003
	private String url;

	public String getStbId() {
		return stbId;
	}

	public void setStbId(String stbId) {
		this.stbId = stbId;
	}

	public String getDvbId() {
		return dvbId;
	}

	public void setDvbId(String dvbId) {
		this.dvbId = dvbId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getStbType() {
		return stbType;
	}

	public void setStbType(String stbType) {
		this.stbType = stbType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
