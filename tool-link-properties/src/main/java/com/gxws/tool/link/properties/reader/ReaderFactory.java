package com.gxws.tool.link.properties.reader;

import com.gxws.tool.common.constant.ProjectConstant;
import com.gxws.tool.link.properties.exception.LinkPropertiesReaderInitException;

/**
 * 获取配置源对象的工厂对象
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.2
 */
public class ReaderFactory {

	/**
	 * 获取Reader对象
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param name
	 *            Reader对象名
	 * @param pc
	 *            ProjectConstant项目信息对象
	 * @return Reader对象
	 * @throws LinkPropertiesReaderInitException
	 *             配置读取对象初始化异常
	 * @since 2.1
	 */
	public static Reader newReaderInstance(String name, ProjectConstant pc) throws LinkPropertiesReaderInitException {
		String readerName = name;
		if (null == readerName) {
			readerName = "file";
		} else {
			readerName = readerName.toLowerCase();
		}
		switch (readerName) {
		case "zookeeper":
			return new ZookeeperReader(pc);
		case "redis":
			return new RedisReader();
		case "http":
			return new HttpReader();
		default:
			return new FileReader(pc);
		}
	}

}
