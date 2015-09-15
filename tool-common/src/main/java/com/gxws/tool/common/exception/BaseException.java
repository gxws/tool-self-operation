package com.gxws.tool.common.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 基础定义异常，用于替代BaseException<br>
 * 重写{@link Exception}的getMessage()方法。<br>
 * 将BaseException对象和其子类对象定义为spring bean。<br>
 * 使用{@link BaseExceptionFactory} 的get()方法获取具体的BaseException类或其子类对象。<br>
 * 
 * @see java.lang.Exception
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.2
 */
public class BaseException extends Exception {
	private static final long serialVersionUID = -1266653452134423857L;

	private static final String PRE = "[";

	private static final String SUF = "]";

	private static final String SEP = ":";

	private StringBuilder message = new StringBuilder();

	private Map<String, String> messageMap = new HashMap<>();

	private String name = "undefind";

	public void appendMessage(String message) {
		this.message.append(PRE + message + SUF);
	}

	// protected void appendMessageKey(String key) {
	// this.message.append(PRE + message);
	// }
	//
	// protected void appendMessageValue(String value) {
	// this.message.append(SEP + message + SUF);
	// }

	protected void appendMessage(String key, String value) {
		// this.message.append(PRE + key + SEP + value + SUF);
		this.messageMap.put(key, value);
	}

	public String getMessage() {
		StringBuilder sb = new StringBuilder(PRE + "异常 " + SEP + this.name + SUF);
		sb.append(message);
		for (Entry<String, String> en : messageMap.entrySet()) {
			sb.append(PRE + en.getKey() + SEP + en.getValue() + SUF);
		}
		return sb.toString();
	}

	public String toString() {
		return getMessage();
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
}
