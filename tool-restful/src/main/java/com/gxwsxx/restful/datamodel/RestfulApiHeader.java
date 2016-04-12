package com.gxwsxx.restful.datamodel;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class RestfulApiHeader {
	public static final String REQUEST_ATTR_NAME = "Restful-Api-Request";
	public static final String SESSION_ATTR_NAME = "Restful-Api-Session";
	public static final String OPERATION_ATTR_NAME = "Restful-Api-Operation";

	private String requestId;
	private String sessionId;
	private String operationId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
}
