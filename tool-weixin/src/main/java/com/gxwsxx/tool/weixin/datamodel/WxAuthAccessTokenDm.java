package com.gxwsxx.tool.weixin.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1.0
 */
public class WxAuthAccessTokenDm extends WxAccessTokenDm {

	@SerializedName("refresh_token")
	private String reflushToken;

	private String openid;

	private String scope;

	public String getReflushToken() {
		return reflushToken;
	}

	public void setReflushToken(String reflushToken) {
		this.reflushToken = reflushToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
