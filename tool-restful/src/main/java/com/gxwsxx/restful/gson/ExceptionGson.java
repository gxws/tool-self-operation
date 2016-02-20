package com.gxwsxx.restful.gson;

/**
 * 异常包装类
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class ExceptionGson<T extends Exception> {

	private String className;
	private String message;
	private T exception;

	public ExceptionGson(T e) {
		exception = e;
		className = e.getClass().getName();
		message = e.getMessage();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getException() {
		return exception;
	}

	public void setException(T exception) {
		this.exception = exception;
	}

}
