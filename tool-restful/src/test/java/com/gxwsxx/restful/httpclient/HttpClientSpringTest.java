package com.gxwsxx.restful.httpclient;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-restful-consumer.xml")
public class HttpClientSpringTest {
	
	@Autowired
	private RestTemplate rt;

	@Test
	public void test() {
		rt.getForObject("http://localhost:20001/demo-service/demo/123", Object.class);
		System.out.println("");
	}

}
