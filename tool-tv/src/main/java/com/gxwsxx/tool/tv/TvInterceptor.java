package com.gxwsxx.tool.tv;

import com.gxwsxx.tool.tv.datamodel.TvDm;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理电视机顶盒访问参数
 *
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class TvInterceptor implements HandlerInterceptor {

    private TvHandler tvHandler = new TvHandler();

    private static final String TV_ATTR_NAME = "tv";

    private String tvAttrName;

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        // 处理webtv用户传入的相关参数
        TvDm tvdm = tvHandler.handleParam(request);
        request.setAttribute(null != tvAttrName ? tvAttrName : TV_ATTR_NAME, tvdm);
        return true;
    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object,
     * org.springframework.web.servlet.ModelAndView)
     */
    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler, ModelAndView modelAndView) throws Exception {
        // 处理时间
        TvDm tvdm = (TvDm) request.getAttribute(null != tvAttrName ? tvAttrName : TV_ATTR_NAME);
        request.setAttribute(null != tvAttrName ? tvAttrName : TV_ATTR_NAME, tvdm);
    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object,
     * java.lang.Exception)
     */
    @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
    }

    public void setTvAttrName(String tvAttrName) {
        this.tvAttrName = tvAttrName;
    }

    public String getTvAttrName() {
        return tvAttrName != null ? tvAttrName : TV_ATTR_NAME;
    }
}
