package com.gxwsxx.tool.web.tv.exception;

import com.gxwsxx.tool.web.tv.data.WebTvUserParam;

/**
 * 电视机顶盒请求所要求的参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvParameterException extends WebTvException {
	private static final long serialVersionUID = 4012399303445443629L;

	protected WebTvUserParam webTvUserParam;

	/**
	 * 电视机顶盒请求所要求的参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @since 1.1
	 */
	public WebTvParameterException() {
		this.name = "电视机顶盒请求所需要的参数异常";
	}

	public WebTvUserParam getWebTvUserParam() {
		return webTvUserParam;
	}

	public void setWebTvUserParam(WebTvUserParam webTvUserParam) {
		this.webTvUserParam = webTvUserParam;
	}

}
