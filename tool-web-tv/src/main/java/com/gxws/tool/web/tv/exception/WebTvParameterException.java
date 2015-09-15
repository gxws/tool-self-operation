package com.gxws.tool.web.tv.exception;

import com.gxws.tool.common.exception.BaseException;

/**
 * 电视机顶盒请求所要求的参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvParameterException extends BaseException {
	private static final long serialVersionUID = 4012399303445443629L;

	private String paramName;

	private String paramDescription;

	/**
	 * 电视机顶盒请求所要求的参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @since 1.1
	 */
	public WebTvParameterException() {
		setName("电视机顶盒请求所要求的参数");
	}

	public String getMessage() {
		appendMessage("参数名", paramName);
		appendMessage("参数说明", paramDescription);
		return super.getMessage();
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamDescription() {
		return paramDescription;
	}

	public void setParamDescription(String paramDescription) {
		this.paramDescription = paramDescription;
	}

	// public void setParam(String name, String description) {
	// setMsg(getMsg() + name + "(" + description + ") ");
	// }

}
