package com.gxwsxx.tool.tv;

/**
 * 电视机顶盒对象异常
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class TvException extends Exception {

    private static final long serialVersionUID = 1903919110197072674L;

    private StringBuilder paramNames = new StringBuilder();

    public String getMessage() {
        return "电视机顶盒其中一项参数输入异常," + paramNames.toString();
    }

    public TvException appendName(String name) {
        this.paramNames.append(name);
        return this;
    }
}
