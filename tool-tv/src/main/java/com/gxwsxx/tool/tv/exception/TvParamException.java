package com.gxwsxx.tool.tv.exception;

import com.gxwsxx.tool.tv.datamodel.TvParam;

/**
 * 电视机顶盒请求所要求的参数
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.1
 */
public class TvParamException extends TvException {
	private static final long serialVersionUID = 4012399303445443629L;

	protected TvParam webTvUserParam;

	public TvParam getTvParam() {
		return webTvUserParam;
	}

	public void setTvParam(TvParam webTvUserParam) {
		this.webTvUserParam = webTvUserParam;
	}

}
