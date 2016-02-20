package com.gxwsxx.restful.consumer.proxy;

import java.lang.reflect.Proxy;
import java.util.Map;

import org.apache.http.client.HttpClient;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class RestfulProxyFactory {

	private HttpClient httpClient;

	private Map<Class<?>, Object> proxyMap;

	private RestfulProxyInvocationHandler pih = new RestfulProxyInvocationHandler();

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> inf) {
		if (proxyMap.containsKey(inf)) {
			return (T) proxyMap.get(inf);
		} else {
			T o = (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { inf }, pih);
			proxyMap.put(inf, o);
			return o;
		}
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
}
