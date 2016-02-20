package com.gxwsxx.tool.properties.spring;

import java.util.List;

/**
 * spring加载静态类名
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class PropertiesConstantBean {

	private List<String> constantClassnames;

	public void setConstantClassnames(List<String> constantClassnames) {
		this.constantClassnames = constantClassnames;
	}

	public List<String> getConstantClassnames() {
		return constantClassnames;
	}
}
