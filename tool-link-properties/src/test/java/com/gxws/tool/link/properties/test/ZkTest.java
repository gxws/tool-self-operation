package com.gxws.tool.link.properties.test;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Version;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author zhuwl120820@gxwsxx.com
 * @since
 */
public class ZkTest {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				System.out.println("已经触发了" + event.getType() + "事件！");
			}
		});

		// // 创建一个目录节点
		// /**
		// * CreateMode:
		// * PERSISTENT (持续的，相对于EPHEMERAL，不会随着client的断开而消失)
		// * PERSISTENT_SEQUENTIAL（持久的且带顺序的）
		// * EPHEMERAL (短暂的，生命周期依赖于client session)
		// * EPHEMERAL_SEQUENTIAL (短暂的，带顺序的)
		// */
		// zk.create("/testRootPath", "testRootData".getBytes(),
		// Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		//
		// // 创建一个子目录节点
		// zk.create("/testRootPath/testChildPathOne",
		// "testChildDataOne".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		// System.out.println(new
		// String(zk.getData("/testRootPath",false,null)));

		// 取出子目录节点列表
		// System.out.println(zk.getChildren("/testRootPath",true));

		// // 创建另外一个子目录节点
		// zk.create("/testRootPath/testChildPathTwo",
		// "testChildDataTwo".getBytes(),
		// Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		// System.out.println(zk.getChildren("/testRootPath",true));
		//
		// // 修改子目录节点数据
		zk.setData("/testRootPath/testChildPathOne", "hahahahaha3".getBytes(), -1);
		byte[] datas = zk.getData("/testRootPath/testChildPathOne", true, null);
		System.out.println(new String(datas, "utf-8"));
		//
		// //删除整个子目录 -1代表version版本号，-1是删除所有版本
		// zk.delete("/testRootPath/testChildPathOne", -1);
		// System.out.println(zk.getChildren("/testRootPath",true));
		// System.out.println(str);
	}
}
