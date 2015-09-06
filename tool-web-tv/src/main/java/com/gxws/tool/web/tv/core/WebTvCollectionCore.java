package com.gxws.tool.web.tv.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gxws.tool.common.constant.ProjectConstant;
import com.gxws.tool.web.tv.data.WebTvUserParam;

/**
 * 电视用户访问信息收集处理类
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1
 */
public class WebTvCollectionCore {

	private static final String ATTR_NAME = "webTvCollection";

	/**
	 * 拼接字符串
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param req
	 *            HttpServletRequest对象
	 * @since 2.1
	 */
	public void handleColletionParam(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object o = session.getAttribute(ATTR_NAME);
		if (null != o) {
			return;
		}
		ProjectConstant pc = (ProjectConstant) req.getServletContext().getAttribute(ProjectConstant.CONTEXT_NAME);
		WebTvUserParam up = (WebTvUserParam) req.getAttribute(WebTvUserParam.ATTR_NAME);
		String value = string(pc.getEnv(), pc.getName(), pc.getVersion(), pc.getIp(), pc.getPort(), up.getStbId(),
				up.getDvbId(), up.getAreaId(), up.getStbType(), session.getId());
		session.setAttribute(ATTR_NAME, value);
	}

	/**
	 * 以"&"符号拼接各项参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param args
	 *            参数数组
	 * @return 拼接字符串
	 * @since 2.1
	 */
	private String string(String... args) {
		if (0 == args.length) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String s : args) {
			sb.append("&");
			if (null == s) {
				continue;
			}
			sb.append(s);
		}
		return sb.substring(1);
	}
}
