package com.gxwsxx.tool.tv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gxwsxx.tool.tv.core.TvNowTimeHandle;
import com.gxwsxx.tool.tv.core.TvUserParamHandle;

/**
 * 处理电视机顶盒访问参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class TvInterceptor implements HandlerInterceptor {

	private TvUserParamHandle pCore = new TvUserParamHandle();

	private TvNowTimeHandle tCore = new TvNowTimeHandle();

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 处理webtv用户传入的相关参数
		pCore.handleWebTvUserParam(request);
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
		tCore.handleWebTvTime(request);
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

}
