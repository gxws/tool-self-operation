package com.gxwsxx.tool.tv.exception;

/**
 * 电视机顶盒请求所要求的参数缺失异常
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class TvUserParamMissingException extends TvParamException {
	private static final long serialVersionUID = -3670342752299034585L;

	private String paramName;

	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(paramName);
		return sb.toString();
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
}
