package com.gxwsxx.tool.weixin.controller;

import com.gxwsxx.tool.weixin.WeixinBo;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1
 */
//@Controller
public class WxPushController {

//	private static final Logger log = LogManager.getLogger(WxPushController.class);

	private WeixinBo wxbo;

//	@RequestMapping(path = "/wxpush", method = RequestMethod.GET)
//	@ResponseBody
	public String wxpush(String signature, String echostr, String timestamp, String nonce) {
		if (wxbo.isWxPush(signature, timestamp, nonce)) {
//			log.debug("是微信推送消息");
		} else {
//			log.debug("不是微信推送消息");
		}
		return echostr;
	}

}
