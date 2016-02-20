package com.gxwsxx.tool.properties.source;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 手动获取属性值的reader
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class ManuallySource implements PropertiesSource {

	private final static Logger log = LoggerFactory.getLogger(ManuallySource.class);

	/**
	 * 获取网卡IP地址
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return ip地址，以","分隔
	 * @since 1.0
	 */
	private String ips() {
		Enumeration<NetworkInterface> netInterfaces;
		Pattern p = Pattern.compile("^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
		Matcher m = null;
		String ip = null;
		StringBuffer sb = new StringBuffer();
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface nif = netInterfaces.nextElement();
				Enumeration<InetAddress> iparray = nif.getInetAddresses();
				while (iparray.hasMoreElements()) {
					ip = iparray.nextElement().getHostAddress();
					m = p.matcher(ip);
					if (m.matches() && !ip.startsWith("127")) {
						sb.append("," + ip);
					}
				}
			}
			if (0 == sb.length()) {
				throw new Exception();
			} else {
				return sb.substring(1);
			}
		} catch (Exception e) {
			log.error("读取不到当前服务器IP", e);
		}
		return null;
	}

	/**
	 * @see com.gxwsxx.tool.properties.source.PropertiesSource#get(com.gxwsxx.tool.properties.entity.PropertiesEntity)
	 */
	@Override
	public void get(PropertiesEntity entity) {
		String value = null;
		switch (entity.getKey()) {
		case "project.ip":
			value = ips();
			break;
		case "project.hostname":
			value = "";
			break;
		default:
			log.error("属性" + entity.getKey() + "没有合适的值");
			break;
		}
		if (null == value) {
			log.error("没有能读取到属性" + entity.getKey());
		} else {
			entity.setValue(value);
		}
	}
}
