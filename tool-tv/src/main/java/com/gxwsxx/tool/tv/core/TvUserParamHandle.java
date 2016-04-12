package com.gxwsxx.tool.tv.core;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.gxwsxx.tool.tv.annotation.TvUserParameter;
import com.gxwsxx.tool.tv.datamodel.StbType;
import com.gxwsxx.tool.tv.datamodel.TvParam;
import com.gxwsxx.tool.tv.exception.TvUserParamIllegalException;
import com.gxwsxx.tool.tv.exception.TvUserParamMissingException;

/**
 * 电视用户访问参数处理类
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class TvUserParamHandle {

	private static final Pattern STB_0203 = Pattern.compile("(Safari)|(Chrome)", Pattern.CASE_INSENSITIVE);

	/**
	 * 处理request参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param req
	 * @return tv 相关参数
	 * @throws TvUserParamMissingException
	 *             必要的参数缺失
	 * @throws IllegalArgumentException
	 *             非法参数异常
	 * @throws IllegalAccessException
	 *             非法访问权限异常
	 * @throws TvUserParamIllegalException
	 *             必要的参数非法异常
	 * @throws UnsupportedEncodingException
	 *             不支持的编码格式异常
	 * @since
	 */
	public TvParam handleWebTvUserParam(HttpServletRequest req)
			throws TvUserParamMissingException, IllegalArgumentException, IllegalAccessException,
			TvUserParamIllegalException, UnsupportedEncodingException {
		TvParam param = new TvParam();
		// 处理请求参数
		Class<TvParam> cls = TvParam.class;
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			handleField(f, param, req);
		}
		StbType type = handleStbType(param, req);
		param.setType(type.getKey());
		// 根据机顶盒类型，设置request编码方式
		if (StbType.ONE.equals(type)) {
			req.setCharacterEncoding("gb2312");
		}
		// 处理url
		String url = handleUrlParam(param);
		param.setUrlparam(url);
		req.setAttribute(TvParam.ATTRIBUTE_NAME, param);
		return param;
	}

	/**
	 * 处理字段，通过注解
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param f
	 *            需要处理 的字段
	 * @param param
	 *            web tv 相关参数对象
	 * @param req
	 *            HttpServletRequest
	 * @throws TvUserParamMissingException
	 *             必要的参数缺失
	 * @throws IllegalArgumentException
	 *             非法参数异常
	 * @throws IllegalAccessException
	 *             非法访问权限异常
	 * @since 1.1
	 */
	public void handleField(Field f, TvParam param, HttpServletRequest req)
			throws TvUserParamMissingException, IllegalArgumentException, IllegalAccessException {
		TvUserParameter ann = f.getAnnotation(TvUserParameter.class);
		if (null == ann) {
			return;
		}
		String v = null;
		for (String s : ann.name()) {
			if ("".equals(s)) {
				s = f.getName();
			}
			v = req.getParameter(s);
			if (null != v) {
				f.setAccessible(true);
				f.set(param, v);
				return;
			}
		}
		if (null == v && ann.require()) {
			TvUserParamMissingException e = new TvUserParamMissingException();
			e.setTvParam(param);
			e.setParamName(f.getName());
			throw e;
		}
	}

	/**
	 * 将tv相关参数处理为url参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param param
	 *            web tv参数对象
	 * @return 返回url参数
	 * @throws IllegalArgumentException
	 *             非法参数异常
	 * @throws IllegalAccessException
	 *             非法访问权限异常
	 * @since 1.1
	 */
	public String handleUrlParam(TvParam param) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		Class<TvParam> cls = TvParam.class;
		Field[] fields = cls.getDeclaredFields();
		TvUserParameter ann = null;
		Object value = null;
		for (Field f : fields) {
			ann = f.getAnnotation(TvUserParameter.class);
			if (null == ann) {
				continue;
			}
			f.setAccessible(true);
			value = f.get(param);
			if (null == value) {
				value = "";
			}
			sb.append("&");
			sb.append(getName(ann, f));
			sb.append("=");
			sb.append(value.toString());
		}
		return sb.toString();
	}

	/**
	 * 处理机顶盒类型
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param param
	 *            web tv参数对象
	 * @param req
	 *            HttpServletRequest对象
	 * @return 机顶盒类型枚举对象
	 * @throws TvUserParamIllegalException
	 *             必要的参数非法异常
	 * @since 1.1
	 */
	public StbType handleStbType(TvParam param, HttpServletRequest req) throws TvUserParamIllegalException {
		StbType type = StbType.fromValue(param.getType());
		if (null == type) {
			int i = Integer.parseInt(param.getType());
			type = StbType.values()[i];
		}
		if (StbType.THREE.equals(type)) {
			return StbType.THREE;
		}
		String ua = req.getHeader("User-Agent");
		Matcher m2 = STB_0203.matcher(ua);
		if (m2.find()) {
			return StbType.TWO;
		} else {
			return StbType.ONE;
		}
	}

	/**
	 * 获取参数名
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param ann
	 *            web tv 注解对象
	 * @param f
	 *            字段
	 * @return 参数名
	 * @since 1.1
	 */
	private String getName(TvUserParameter ann, Field f) {
		String name = ann.name()[0];
		if ("".equals(name)) {
			name = f.getName();
		}
		return name;
	}

}
