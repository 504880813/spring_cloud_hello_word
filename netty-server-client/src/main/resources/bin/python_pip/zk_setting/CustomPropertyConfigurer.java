package com.yyxb.framework.core.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;

import com.alibaba.dubbo.common.utils.CollectionUtils;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

public class CustomPropertyConfigurer extends PropertyPlaceholderConfigurer {

	private Logger logger = LoggerFactory.getLogger(CustomPropertyConfigurer.class);
	
	private static Map<String, String> properties = new HashMap<String, String>();
	// zookeeper注册中心地址
	private static final String ZOOKEEPER_SERVER_ADDRESS = "zookeeper.address";
	// zookeeper属性配置文件路径
	private static final String ZOOKEEPER_PROPERTIES_PATH = "zookeeper.properties.path";
    //zookeeper前缀
	private static final String PFEX="zookeeper://";
	//zookeeper字符串
	private static final String WEN_HAO_STR="?backup=";
	//zookeeper逗号分割符号
	private static final String DOU_HAO_STR=",";
	
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		// cache the properties
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX,
				DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);
		for (Entry<Object, Object> entry : props.entrySet()) {

			String stringKey = String.valueOf(entry.getKey());
			String stringValue = String.valueOf(entry.getValue());
			stringValue = helper.replacePlaceholders(stringValue, props);
			properties.remove(stringKey);
			properties.put(stringKey, stringValue);
		}
		logger.info("---从Zookeeper注册中心获取项目属性文件配置开始---");
		try {
			//满足连接zk集群
			String zookeepeServerAddress = props.getProperty(ZOOKEEPER_SERVER_ADDRESS).replace(WEN_HAO_STR, DOU_HAO_STR);
			String zookeeperPropertiesPath = props.getProperty(ZOOKEEPER_PROPERTIES_PATH);
			zookeepeServerAddress=zookeepeServerAddress.substring(PFEX.length(),zookeepeServerAddress.length());
			Map<String, String> properties_list = this.getZookeeperPropertyConfigurer(zookeepeServerAddress,
					zookeeperPropertiesPath);
			props.putAll(properties_list);
			properties.putAll(properties_list);
		} catch (Exception e) {
			logger.info("从Zookeeper获取项目属性文件配置异常!" + e.getMessage(), e);
		}
		logger.info("---从Zookeeper注册中心获取项目属性文件配置结束---");
		super.processProperties(beanFactoryToProcess, props);
	}

	private Map<String, String> getZookeeperPropertyConfigurer(String zookeepeServerAddress,
			String zookeeperPropertiesPath) throws Exception {
		if (StringUtils.isBlank(zookeepeServerAddress)) {
			throw new Exception("Zookeeper服务器地址不能为空！");
		}
		ZookeeperOperater operate = new ZookeeperOperater(zookeepeServerAddress);		
		List<String> paths = operate.getSubPaths(zookeeperPropertiesPath);
		Map<String, String> map = new HashMap<String, String>();
		// 遍历所有子节点，以及节点值
		if (CollectionUtils.isNotEmpty(paths) == true) {
			// 遍历有子节点
			for (String path : paths) {
				byte[] data = operate.getPathValue(zookeeperPropertiesPath + "/" + path);
				if (data != null) {
					String value = new String(data, "UTF-8");
					if (StringUtils.isNotBlank(value) == true) {
						map.put(path, value);
					}
				}
			}
		}
		operate.close();
		return map;
	}

	public static Map<String, String> getProperties() {
		return properties;
	}

	public static String getProperty(String key) {
		return properties.get(key);
	}
}
