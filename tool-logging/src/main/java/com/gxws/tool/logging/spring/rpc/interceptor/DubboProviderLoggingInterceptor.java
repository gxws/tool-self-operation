package com.gxws.tool.logging.spring.rpc.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.gxws.tool.common.data.dto.BaseDto;
import com.gxws.tool.common.uuid.Uuid;
import com.gxws.tool.logging.constant.LoggingContextMapConstant;
import com.gxws.tool.logging.constant.LoggingMarkerConstant;

/**
 * 服务提供方通过dubbo远程调用记录日志信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class DubboProviderLoggingInterceptor implements Filter {

	private Logger log = LogManager.getLogger();

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String interfaceName = invocation.getAttachment("interface");
		String methodName = invocation.getMethodName();
		log.debug(LoggingMarkerConstant.DUBBO_FILTER_MARKER, "接收dubbo rpc请求:" + interfaceName + "." + methodName);
		Object[] os = invocation.getArguments();
		Map<String, String> reqMap = null;
		Map<String, String> paramMap = new HashMap<>();
		ThreadContext.put(LoggingContextMapConstant.SERVICE_REQUEST, Uuid.order());
		if (null == os || 0 == os.length) {
			log.debug(LoggingMarkerConstant.DUBBO_FILTER_MARKER, "无参数传入方法");
		} else {
			for (Object o : os) {
				if (o instanceof BaseDto) {
					reqMap = ((BaseDto) o).getRequestMap();
					((BaseDto) o).setResponseMap(ThreadContext.getContext());
					if (null != reqMap && !reqMap.isEmpty()) {
						paramMap.putAll(reqMap);
					}
				} else {
					log.warn(LoggingMarkerConstant.DUBBO_FILTER_MARKER,
							"参数:" + o.getClass().getName() + "不是" + BaseDto.class.getName() + "的子类");
				}
			}
			if (!paramMap.isEmpty()) {
				for (Entry<String, String> en : paramMap.entrySet()) {
					ThreadContext.put(en.getKey(), en.getValue());
				}
			}
		}
		return invoker.invoke(invocation);
	}
}
