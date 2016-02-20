package com.gxwsxx.restful.gson;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gxwsxx.restful.gson.EnumTypeAdapter;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class GsonTest {

	@Test
	public void test() {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeHierarchyAdapter(Exception.class, new EnumTypeAdapter());
		HhdException e = new HhdException();
		Gson g = gb.create();
		System.out.println(g.toJson(e));
	}

}
