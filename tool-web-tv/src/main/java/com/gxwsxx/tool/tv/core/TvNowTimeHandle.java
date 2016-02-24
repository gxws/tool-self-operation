package com.gxwsxx.tool.tv.core;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

/**
 * 机顶盒获取当前时间处理类
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 2.1
 */
public class TvNowTimeHandle {

	private static final int[] CALENDAR_ARR = new int[] { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,
			Calendar.DAY_OF_WEEK, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };

	private static final String ATTR_NAME = "nowTime";

	/**
	 * 处理机顶盒展示要求的时间格式
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param req
	 *            HttpServletRequest对象
	 * @since 2.1
	 */
	public void handleWebTvTime(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		Calendar c = Calendar.getInstance();
		for (int i : CALENDAR_ARR) {
			sb.append("|");
			if (i == Calendar.MONTH) {
				sb.append(c.get(i) + 1);
			} else if (i == Calendar.DAY_OF_WEEK) {
				if (1 == c.get(i)) {
					sb.append("7");
				} else {
					sb.append(c.get(i) - 1);
				}
			} else {
				sb.append(c.get(i));
			}
		}
		req.setAttribute(ATTR_NAME, sb.substring(1));
	}
}
