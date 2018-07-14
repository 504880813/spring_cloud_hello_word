/**   
* @Title: ZookeeperOperate.java 
* @Package com.yyxb.framework.core.utils 
* @Description: 
* @author lance
* @date 2017年3月14日 下午2:02:21 
* @version V1.0   
*/
package com.yyxb.framework.core.utils;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @功能描述: zookeeper操作类
 * @项目版本: V1.0
 * @项目名称: 衣衣相伴-支付平台
 * @创建作者: lance
 * @创建日期: 2017年3月14日 下午2:02:21
 * @ClassName: ZookeeperOperate
 */
public class ZookeeperOperater implements Watcher {

	private Logger logger = LoggerFactory.getLogger(ZookeeperOperater.class);

	private String zookeepeServerAddress;

	private ZooKeeper zookeeper;

	private static final int SESSION_TICK_TIME = 90000;

	public ZookeeperOperater(String zookeepeServer) {
		this.zookeepeServerAddress = zookeepeServer;
		try {
			zookeeper = new ZooKeeper(zookeepeServerAddress, SESSION_TICK_TIME, this);
		} catch (Exception e) {
			logger.info("初始化连接zookeeper失败" + e.getMessage(), e);
			e.printStackTrace();
		}
	}

	/*
	 * Title: process Description:
	 * 
	 * @param event
	 * 
	 * @see
	 * org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	 */
	public void process(WatchedEvent event) {
		// Auto-generated method stub
		// 节点访问、 节点操作等都不做处理哦
	}

	public void close() throws InterruptedException {
		zookeeper.close();
	}

	/**
	 * 获取节点list
	 */
	public List<String> getSubPaths(String path) throws Exception {
		return zookeeper.getChildren(path, false);
	}

	/**
	 * 获取key value值
	 */
	public byte[] getPathValue(String path) throws Exception {
		if (!exists(this.zookeeper, path)) {
			throw new RuntimeException(path + "路径不存在");
		}
		return zookeeper.getData(path, false, null);
	}
    /**
    * 判断节点是否操作
    */
	private boolean exists(ZooKeeper zookeeper, String path) throws Exception {
		Stat stat = zookeeper.exists(path, false);
		return !(stat == null);
	}

	/**
	 * 
	* @功能描述: <p>测试使用</p>
	 */
	public void create() {
		try {
			if (exists(this.zookeeper, "/com")) {
				zookeeper.delete("/com/yyxb/passport/mysql.driverClassName", -1);
				zookeeper.delete("/com/yyxb/passport", -1);
				zookeeper.delete("/com/yyxb", -1);
				zookeeper.delete("/com", 0);
			}
			zookeeper.create("/com", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zookeeper.create("/com/yyxb", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zookeeper.create("/com/yyxb/passport", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zookeeper.create("/com/yyxb/passport/mysql.driverClassName", "com.mysql.jdbc.Driver".getBytes(),
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (InterruptedException e) {
			// Auto-generated method stub
			e.printStackTrace();
		} catch (KeeperException e) {
			// Auto-generated method stub
			e.printStackTrace();
		} catch (Exception e) {
			// Auto-generated method stub
			e.printStackTrace();
		}
	}
}
