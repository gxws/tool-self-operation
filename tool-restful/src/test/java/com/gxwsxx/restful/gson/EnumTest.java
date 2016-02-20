package com.gxwsxx.restful.gson;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gxwsxx.restful.gson.dm.GsonDmEnum;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class EnumTest {

	@Test
	public void test() {
		GsonDmEnum en = GsonDmEnum.NO_1;
		Class<?> clz = en.getClass();
		boolean b1 = clz.isAssignableFrom(Enum.class);
		boolean b2 = Enum.class.isAssignableFrom(clz);
	}

}
