package com.gxwsxx.restful.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException.Code;

import java.util.Arrays;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class ZkWatcher implements Watcher, StatCallback {

	private ZooKeeper zk;

	/**
	 * @see org.apache.zookeeper.AsyncCallback.StatCallback#processResult(int,
	 *      java.lang.String, java.lang.Object, org.apache.zookeeper.data.Stat)
	 */
	@Override
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		System.out.println(rc);
		switch (rc) {
		case Code.Ok:
			break;
		case Code.NoNode:
			break;
		case Code.SessionExpired:
			break;
		case Code.NoAuth:
			break;
		default:
			return;
		}

	}

	/**
	 * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	 */
	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub

	}

}
