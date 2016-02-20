package com.gxwsxx.restful.gson;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class DateTypeAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {

	private static final SimpleDateFormat SDF_DEFAULT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
	 *      java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(SDF_DEFAULT.format(src));
	}

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement,
	 *      java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		String v = json.getAsJsonPrimitive().getAsString();
		String sdfPattern = "";
		if (-1 != v.indexOf('/')) {
			sdfPattern = "yyyy/MM/dd HH:mm:ss";
		} else if (-1 != v.indexOf('-')) {
			sdfPattern = "yyyy-MM-dd HH:mm:ss";
		} else {
			long vl = 0L;
			try {
				vl = Long.valueOf(v);
			} catch (Exception e) {
				throw new JsonParseException(e.getMessage(), e);
			}
			if (vl > 0) {
				return new Date(Long.valueOf(v));
			} else {
				throw new JsonParseException("");
			}
		}
		if (-1 != v.indexOf('.')) {
			sdfPattern = sdfPattern + ".SSS";
		}
		try {
			return new SimpleDateFormat(sdfPattern).parse(v);
		} catch (ParseException e) {
			throw new JsonParseException(e.getMessage(), e);
		}
	}

}
