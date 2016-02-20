package com.gxwsxx.tool.properties.source;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.gxwsxx.tool.properties.entity.PropertiesEntity;

/**
 * 通过zookeeper实现的配置源中获取配置信息
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 3.0
 */
public class ZookeeperSource implements PropertiesSource, InitializingBean {
	private final static Logger log = LoggerFactory.getLogger(ZookeeperSource.class);

	private CuratorFramework curatorFramework;

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public CuratorFramework getCuratorFramework() {
		return curatorFramework;
	}

	public void setCuratorFramework(CuratorFramework curatorFramework) {
		this.curatorFramework = curatorFramework;
	}

	/**
	 * @see com.gxwsxx.tool.properties.source.PropertiesSource#get(com.gxwsxx.tool.properties.entity.PropertiesEntity)
	 */
	@Override
	public void get(PropertiesEntity entity) {
		// TODO Auto-generated method stub

	}

}
