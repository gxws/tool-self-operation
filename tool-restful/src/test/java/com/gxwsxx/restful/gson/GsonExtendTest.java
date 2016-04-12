package com.gxwsxx.restful.gson;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gxwsxx.restful.gson.dm.GsonDmSub;
import com.gxwsxx.restful.gson.dm.GsonDmSuper;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class GsonExtendTest {

	@Test
	public void test() {
		Gson g = new GsonBuilder().create();
		GsonDmSub sub = new GsonDmSub();
		GsonDmSuper sup = sub;
		System.out.println(g.toJson(sup));
	}

}
