package com.gxwsxx.restful.httpclient;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class HttpClientTest2 {

	@Test
	public void test() {
		HttpClientBuilder hcb = HttpClientBuilder.create();
		
		PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager();
		pcm.setMaxTotal(100);
		pcm.setDefaultMaxPerRoute(2);

		CloseableHttpClient hc = hcb.setConnectionManager(pcm).build();


		for (int i = 0; i < 100; i++) {
			HttpGet hg1 = new HttpGet();
			try {
				CloseableHttpResponse res1 = hc.execute(hg1);
				HttpEntity en1 = res1.getEntity();
				en1.getContent().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}

	}

}
