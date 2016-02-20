package com.gxwsxx.restful.gson;

import static org.junit.Assert.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class ListTest {

	@SuppressWarnings("unused")
	@Test
	public <E> void test() {
		List<Throwable> list = new ArrayList<>();
		Class<?> clazz = list.getClass();
		ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
//		Type t = pt.getRawType();
		Class t =  (Class) pt.getActualTypeArguments()[0];
	}
	
}
