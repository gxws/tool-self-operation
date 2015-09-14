package com.gxws.tool.common.exception;

/**
 * 基础定义异常，用于替代BaseException<br>
 * 重写{@link Exception}的getMessage()方法。
 * 
 * @see java.lang.Exception
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1
 */
public class BaseDefinitionException extends Exception {

	private static final long serialVersionUID = 6045066284191405667L;

	protected String message;

	protected String code = "undefind";

	public String getMessage() {
		return "[exception code = " + code + "]" + message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
