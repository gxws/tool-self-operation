package com.gxwsxx.restful.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 处理枚举类，序列化和反序列化
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class EnumTypeAdapter implements JsonDeserializer<Enum<?>>, JsonSerializer<Enum<?>> {

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
	 *      java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(Enum<?> src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.ordinal());
	}

	/**
	 * @param <T>
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement,
	 *      java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public Enum<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Class<?> cls = (Class<?>) typeOfT;
		Enum<?>[] obj = (Enum<?>[]) cls.getEnumConstants();
		int i = 0;
		try {
			i = json.getAsJsonPrimitive().getAsInt();
		} catch (Exception e) {
			throw new JsonParseException("");
		}
		return obj[i];
	}

}
