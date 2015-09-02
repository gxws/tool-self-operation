package com.gxws.tool.web.tv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gxws.tool.web.tv.core.WebTvCollectionCore;
import com.gxws.tool.web.tv.core.WebTvNowTimeCore;
import com.gxws.tool.web.tv.core.WebTvUserParameterCore;

/**
 * 处理电视机顶盒访问参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class WebTvInterceptor implements HandlerInterceptor {

	private WebTvUserParameterCore pCore = new WebTvUserParameterCore();

	private WebTvCollectionCore cCore = new WebTvCollectionCore();

	private WebTvNowTimeCore tCore = new WebTvNowTimeCore();

	// 收集用户信息url
	private String webTvCollectUrl;

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 处理参数
		pCore.handleRequest(request);
		return true;
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 处理时间
//		pCore.handleWebTvTime(request);
		tCore.handleWebTvTime(request);
		cCore.handleColletionUrl(request, webTvCollectUrl);
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public String getWebTvCollectUrl() {
		return webTvCollectUrl;
	}

	public void setWebTvCollectUrl(String webTvCollectUrl) {
		this.webTvCollectUrl = webTvCollectUrl;
	}

}
