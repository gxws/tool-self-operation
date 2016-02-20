package com.gxwsxx.restful.consumer;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class HttpClientFactoryBean implements FactoryBean<HttpClient>, InitializingBean {

	private HttpClient httpClient;

	private HttpRequestRetryHandler httpRequestRetryHandler;

	private boolean isHostnameVerifier = true;

	private int maxTotal = 100;

	private int defaultMaxPerRoute = 30;

	private List<Header> defaultHeaders;

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		HttpClientBuilder hcb = HttpClientBuilder.create();
		if (null != httpRequestRetryHandler) {
			hcb.setRetryHandler(httpRequestRetryHandler);
		}
		if (!isHostnameVerifier) {
			hcb.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
		}
		hcb.setMaxConnTotal(maxTotal);
		hcb.setMaxConnPerRoute(defaultMaxPerRoute);
		if (null != defaultHeaders) {
			hcb.setDefaultHeaders(defaultHeaders);
		}
		this.httpClient = hcb.build();
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public HttpClient getObject() throws Exception {
		return this.httpClient;
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return HttpClient.class;
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setHttpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
		this.httpRequestRetryHandler = httpRequestRetryHandler;
	}

	public void setHostnameVerifier(boolean isHostnameVerifier) {
		this.isHostnameVerifier = isHostnameVerifier;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	public void setDefaultHeaders(List<Header> defaultHeaders) {
		this.defaultHeaders = defaultHeaders;
	}

}
