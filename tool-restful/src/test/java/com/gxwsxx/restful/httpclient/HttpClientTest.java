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
public class HttpClientTest {

	@Test
	public void test() {
		HttpClientBuilder hcb = HttpClientBuilder.create();
		
		PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager();
		pcm.setMaxTotal(100);
		pcm.setDefaultMaxPerRoute(2);

		CloseableHttpClient hc = hcb.setConnectionManager(pcm).build();

		Builder rcb = RequestConfig.custom();

		for (int i = 0; i < 100; i++) {
			HttpGet hg1 = new HttpGet("http://localhost:20001/demo-service/demo/123");
			HttpGet hg2 = new HttpGet("http://localhost:20001/demo-service/exception/123");
			try {
				CloseableHttpResponse res1 = hc.execute(hg1);
				CloseableHttpResponse res2 = hc.execute(hg2);
				HttpEntity en1 = res1.getEntity();
				HttpEntity en2 = res2.getEntity();
				en1.getContent().close();
				en2.getContent().close();
				// res1.close();
				// res2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}

		// HttpGet hg1 = new
		// HttpGet("http://localhost:20001/demo-service/demo/123");
		// HttpGet hg2 = new
		// HttpGet("http://localhost:20001/demo-service/exception/123");
		// try {
		// CloseableHttpResponse res1 = hc.execute(hg1);
		// CloseableHttpResponse res2 = hc.execute(hg2);
		// res1.getStatusLine();
		// res2.getStatusLine();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

}
