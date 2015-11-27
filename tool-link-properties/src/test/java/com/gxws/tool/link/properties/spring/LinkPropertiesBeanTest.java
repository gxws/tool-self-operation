package com.gxws.tool.link.properties.spring;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gxwsxx.tool.link.properties.datamodel.ProjectInfo;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-spring.xml")
@WebAppConfiguration
public class LinkPropertiesBeanTest {

	@BeforeClass
	public static void beforeClass() {
		System.setProperty("attr_java_opts", "java_opts_value2");
	}

	@Autowired
	private ProjectInfo pi;

	@Test
	public void test() {
		Assert.assertNotNull(pi);
		Assert.assertEquals("env_default", pi.getEnv());
	}

}
