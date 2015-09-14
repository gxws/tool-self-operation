package com.gxws.tool.definition.dto;

import java.io.Serializable;
import java.util.Map;

import com.gxws.tool.definition.exception.BaseDefinitionException;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class BaseDefinitionDto implements Serializable {

	private static final long serialVersionUID = -2854339862355132090L;

	// 提供日志信息
	private Map<String, String> requestMap;

	// 提供日志信息
	private Map<String, String> responseMap;

	// 远程调用是否异常
	private boolean hasException = false;

	// 预定义异常对象
	private BaseDefinitionException exception;

	public void setException(BaseDefinitionException exception) {
		this.hasException = true;
		this.exception = exception;
	}

	public BaseDefinitionException getException() {
		return exception;
	}

	public Map<String, String> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, String> requestMap) {
		this.requestMap = requestMap;
	}

	public Map<String, String> getResponseMap() {
		return responseMap;
	}

	public void setResponseMap(Map<String, String> responseMap) {
		this.responseMap = responseMap;
	}

	public boolean hasException() {
		return hasException;
	}

}
