package com.gxwsxx.tool.weixin.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1
 */
public class WxAccessTokenDm extends WxDm {

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("expires_in")
	private String expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

}
