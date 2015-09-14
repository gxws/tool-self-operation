package com.gxws.tool.definition.exception;

/**
 * 基础定义异常，用于替代BaseException<br>
 * 重写{@link Exception}的getMessage()方法。<br>
 * 将BaseDefinitionException对象和其子类对象定义为spring bean。<br>
 * 使用{@link BaseDefinitionExceptionFactory}
 * 的get()方法获取具体的BaseDefinitionException类或其子类对象。<br>
 * 
 * @see java.lang.Exception
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1
 */
public class BaseDefinitionException extends Exception {

	private static final long serialVersionUID = 6045066284191405667L;

	protected String message;

	protected String name = "undefind";

	public void appendMessage(String message) {
		this.message = this.message + "[" + message + "]";
	}

	public String getMessage() {
		return "[异常:" + this.name + "]" + this.message;
	}

}
