package com.gxwsxx.restful.spring;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gxwsxx.restful.datamodel.RestfulApiHeader;
import com.gxwsxx.restful.exception.ExceptionDto;
import com.gxwsxx.restful.exception.ValidateTypeExceptionDto;
import com.gxwsxx.restful.exception.ValidateValueExceptionDto;
import com.gxwsxx.restful.gson.ExceptionGson;

/**
 * restful controller 扩展处理
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
@ControllerAdvice(annotations = RestController.class)
public class RestfulProviderControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(RestfulProviderControllerAdvice.class);

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
	private static final Random r = new Random();
	private static final DecimalFormat df = new DecimalFormat("00000");

	private ThreadLocal<RestfulApiHeader> tl = new ThreadLocal<>();

	/**
	 * controller抛出异常处理
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param e
	 * @return
	 * @since 3.0
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionGson<ExceptionDto> handleException(Exception e) {
		log.error(e.getMessage(), e);
		ExceptionDto ed = null;
		if (ExceptionDto.class.isAssignableFrom(e.getClass())) {
			ed = (ExceptionDto) e;
		} else {
			ed = new ExceptionDto();
		}
		return new ExceptionGson<ExceptionDto>(ed);
	}

	/**
	 * 处理参数值验证异常
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param e
	 * @return
	 * @since 3.0
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionGson<ValidateValueExceptionDto> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		BindingResult br = e.getBindingResult();
		ValidateValueExceptionDto ve = new ValidateValueExceptionDto();
		for (FieldError fe : br.getFieldErrors()) {
			ve.put(fe.getField(), fe.getRejectedValue(), fe.getDefaultMessage());
		}
		return new ExceptionGson<ValidateValueExceptionDto>(ve);
	}

	/**
	 * 处理参数类型异常
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param e
	 * @return
	 * @since 3.0
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionGson<ValidateTypeExceptionDto> handelHttpMessageNotReadableException(
			HttpMessageNotReadableException e) {
		log.error("", e);
		ValidateTypeExceptionDto ve = new ValidateTypeExceptionDto();
		return new ExceptionGson<ValidateTypeExceptionDto>(ve);
	}

	/**
	 * 处理restful headers参数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param req
	 * @param res
	 * @since 3.0
	 */
	@ModelAttribute
	public void handleHeaders(HttpServletRequest req, HttpServletResponse res) {
		RestfulApiHeader rah = new RestfulApiHeader();
		// 读取request id
		String request = req.getHeader(RestfulApiHeader.REQUEST_ATTR_NAME);
		res.setHeader(RestfulApiHeader.REQUEST_ATTR_NAME, request);
		MDC.put(RestfulApiHeader.REQUEST_ATTR_NAME, request);
		rah.setRequestId(request);

		// 读取session id
		String session = req.getHeader(RestfulApiHeader.SESSION_ATTR_NAME);
		res.setHeader(RestfulApiHeader.SESSION_ATTR_NAME, session);
		MDC.put(RestfulApiHeader.SESSION_ATTR_NAME, session);
		rah.setSessionId(session);

		// 生成operation id
		String operation = operation();
		MDC.put(RestfulApiHeader.OPERATION_ATTR_NAME, operation);
		res.setHeader(RestfulApiHeader.OPERATION_ATTR_NAME, operation);
		rah.setOperationId(operation);

		// 属性放入threadlocal
		tl.set(rah);
	}

	private String operation() {
		String oid = sdf.format(new Date());
		return oid + df.format(r.nextInt(99999));
	}

}
