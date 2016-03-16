package com.gxwsxx.tool.weixin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;

import com.google.gson.Gson;
import com.gxwsxx.tool.weixin.datamodel.WxAccessTokenDm;
import com.gxwsxx.tool.weixin.datamodel.WxAuthAccessTokenDm;

/**
 * 微信模块
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1.0
 */
//@Service
public class WeixinBo {


	private String accessToken;
	
	private String appID;

	private String appsecret;

	private String encodingAESKey;

	private String token;

	private HttpClient hc;

	private Gson gson;

	/**
	 * 获取access_token
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @since 2.1
	 */
//	@Scheduled(cron = "0 0 */1 * * ?")
	@PostConstruct
	public void accessToken() {
		HttpGet hg = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID
				+ "&secret=" + appsecret);
		try {
			HttpResponse hr = hc.execute(hg);
			WxAccessTokenDm at = wxResponse(hr, WxAccessTokenDm.class);
			if (0 == at.getErrcode()) {
				accessToken = at.getAccessToken();
			} else {
				throw new IOException("微信接口返回错误，错误码：" + at.getErrcode() + " 错误原因：" + at.getErrmsg());
			}
		} catch (IOException e) {
//			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 使用用户授权的code获取openid
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param code
	 *            用户授权的code
	 * @return 获取微信用户的openid
	 * @since 2.1
	 */
	public String openid(String code) {
		HttpGet hg = new HttpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appID + "&secret="
				+ appsecret + "&code=" + code + "&grant_type=authorization_code");
		try {
			HttpResponse hr = hc.execute(hg);
			WxAuthAccessTokenDm aat = wxResponse(hr, WxAuthAccessTokenDm.class);
			if (0 == aat.getErrcode()) {
				return aat.getOpenid();
			} else {
				throw new IOException("微信接口返回错误，错误码：" + aat.getErrcode() + " 错误原因：" + aat.getErrmsg());
			}
		} catch (IOException e) {
//			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 接收到推送消息的验证
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param signature
	 *            sha1验证码
	 * @param params
	 *            参数
	 * @return 是否是微信推送
	 * @since 2.1
	 */
	public boolean isWxPush(String signature, String... params) {
		if (null == signature || signature.isEmpty()) {
			return false;
		}
		List<String> paramslist = new ArrayList<>(Arrays.asList(params));
		paramslist.add(token);
		Collections.sort(paramslist);
		StringBuilder sb = new StringBuilder();
		for (String param : paramslist) {
			sb.append(param);
		}
		String sign = DigestUtils.sha1Hex(sb.toString());
		System.out.println("     sign:" + sign);
		System.out.println("signature:" + signature);
		if (sign.equals(signature)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 处理请求微信接口后，微信返回的json应答
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param hr
	 *            http response对象
	 * @param cls
	 *            反序列化对象的类
	 * @return 应答信息的反序列化对象
	 * @throws UnsupportedOperationException
	 *             不支持的编码格式异常
	 * @throws IOException
	 *             其他异常
	 * @since 2.1
	 */
	private <T> T wxResponse(HttpResponse hr, Class<T> cls) throws UnsupportedOperationException, IOException {
		StatusLine statusLine = hr.getStatusLine();
		HttpEntity entity = hr.getEntity();
		if (statusLine.getStatusCode() >= 300) {
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
		Reader reader = new InputStreamReader(entity.getContent(), "utf-8");
		return gson.fromJson(reader, cls);
	}

}
