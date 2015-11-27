package com.gxws.tool.web.tv.interceptor;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gxwsxx.tool.web.tv.data.WebTvUserParam;

@WebAppConfiguration
@ContextConfiguration(locations = { "/test-spring.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class WebTvInterceptorTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	MockHttpServletRequest request;

	private MockMvc mockMvc;

	@BeforeClass
	private void beforeClass() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void preHandleWithParam() throws Exception {
		String param = "stbId=123&dvbId=213&areaId=321&stbType=0003";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?" + param))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNotNull(result.getModelAndView().getViewName());
		WebTvUserParam p = (WebTvUserParam) result.getRequest().getAttribute(WebTvUserParam.ATTRIBUTE_NAME);
		Assert.assertEquals(p.getStb(), "123");
		Assert.assertEquals(p.getIdc(), "213");
		Assert.assertEquals(p.getArea(), "321");
		Assert.assertEquals(p.getType(), "0003");
		Assert.assertEquals(p.getUrlparam(), "&" + param);
		// System.out.println(p.getTime());
	}

	@Test
	public void preHandleMissingParam0() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/testurl1")).andDo(MockMvcResultHandlers.print()).andReturn();
	}

	@Test
	public void preHandleMissingParam1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?stbId=123")).andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void preHandleMissingParam2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?stbId=123&dvbId=213"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

	@Test
	public void preHandleMissingParam3() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?stbId=123&dvbId=213&areaId=321"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

	@Test
	public void preHandleMissingParam4() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?device_id=123")).andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void preHandleMissingParam5() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?device_id=123&user_id=213"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

	@Test
	public void preHandleMissingParam6() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("/testurl1?device_id=123&user_id=213&area_code=321&stbType=0002").header("User-Agent", "abc"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNotNull(result.getModelAndView().getViewName());
		WebTvUserParam p = (WebTvUserParam) result.getRequest().getAttribute(WebTvUserParam.ATTRIBUTE_NAME);
		Assert.assertEquals(p.getStb(), "123");
		Assert.assertEquals(p.getIdc(), "213");
		Assert.assertEquals(p.getArea(), "321");
		Assert.assertEquals(p.getType(), "0001");
		Assert.assertEquals(p.getUrlparam(), "&stbId=123&dvbId=213&areaId=321&stbType=0001");
	}

	@Test
	public void preHandleMissingParam61() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("/testurl1?device_id=123&user_id=213&area_code=321&device_type=0002").header("User-Agent", "abc"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNotNull(result.getModelAndView().getViewName());
		WebTvUserParam p = (WebTvUserParam) result.getRequest().getAttribute(WebTvUserParam.ATTRIBUTE_NAME);
		Assert.assertEquals(p.getStb(), "123");
		Assert.assertEquals(p.getIdc(), "213");
		Assert.assertEquals(p.getArea(), "321");
		Assert.assertEquals(p.getType(), "0001");
		Assert.assertEquals(p.getUrlparam(), "&stbId=123&dvbId=213&areaId=321&stbType=0001");
	}

	@Test
	public void preHandleMissingParam7() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/testurl1?device_id=123&user_id=213&area_code=321&stbType=0002")
						.header("User-Agent",
								"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		Assert.assertNotNull(result.getModelAndView().getViewName());
		WebTvUserParam p = (WebTvUserParam) result.getRequest().getAttribute(WebTvUserParam.ATTRIBUTE_NAME);
		Assert.assertEquals(p.getStb(), "123");
		Assert.assertEquals(p.getIdc(), "213");
		Assert.assertEquals(p.getArea(), "321");
		Assert.assertEquals(p.getType(), "0002");
		Assert.assertEquals(p.getUrlparam(), "&stbId=123&dvbId=213&areaId=321&stbType=0002");
	}

	@Test
	public void preHandleIllegalParam() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/testurl1?device_id=123&user_id=213&area_code=321&stbType=0004")
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

}
