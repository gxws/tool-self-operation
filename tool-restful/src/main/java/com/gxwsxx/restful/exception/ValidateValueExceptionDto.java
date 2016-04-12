package com.gxwsxx.restful.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@ExceptionLabel("参数值验证错误异常")
public class ValidateValueExceptionDto extends ExceptionDto {

	private static final long serialVersionUID = 7470873641389030035L;

	private List<ValidField> fields = new ArrayList<>();

	public void put(String field, Object value, String tip) {
		ValidField vf = new ValidField();
		vf.setField(field);
		vf.setValue(value);
		vf.setTip(tip);
		fields.add(vf);
	}

	private class ValidField {
		private String field;
		private Object value;
		private String tip;

		public void setField(String field) {
			this.field = field;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public void setTip(String tip) {
			this.tip = tip;
		}
	}

	public List<ValidField> getFields() {
		return fields;
	}

}
