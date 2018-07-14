#!/usr/bin/env python
#coding=utf-8

import logging
logging.basicConfig()

from kazoo.client import KazooClient
def operate_zk_node(src):
    print " ----------start-------------------"
    src_host=KazooClient(hosts=src)
    src_host.start()

    print "create path:"
    path_p='/com/yyxb/password'

    print "define  array list:"
    pathList=[
    (1,'/com',''),
    (2,'/com/yyxb',''),
    (3,'/com/yyxb/password',''),
    ###################################################################
    #mysql setting
    (999,'/mysql.driverClassName','com.mysql.jdbc.Driver'),
    (999,'/jdbc.pool.validationQuery','select current_timestamp()'),
    (999,'/mysql.url','jdbc:mysql://192.168.2.216:3306/passport?useUnicode\=true&characterEncoding\=utf8&autoReconnect\=true&zeroDateTimeBehavior\=convertToNull&allowMultiQueries\=true'),
    (999,'/mysql.username','root'),
    (999,'/mysql.password','root'),
    (999,'/mysql.logAbandoned','true'),
    (999,'/mysql.removeAbandoned','true'),
    (999,'/mysql.removeAbandonedTimeout','180'),
    (999,'/mysql.initialSize','10'),
    (999,'/mysql.maxIdle','20'),
    (999,'/mysql.minIdle','3'),
    (999,'/mysql.maxWait','60000'),
    (999,'/mysql.maxActive','20'),
    (999,'/mysql.minEvictableIdleTimeMillis','1800000'),
    (999,'/mysql.timeBetweenEvictionRunsMillis','1800000'),
    (999,'/mysql.numTestsPerEvictionRun','3'),
    (999,'/mysql.testOnBorrow','false'),
    (999,'/mysql.testOnReturn','false'),
    (999,'/mysql.testWhileIdle','true'),
    ###################################################################
    # Redis setting
    (999,'/redis.sentinel1.host','192.168.2.215'),
    (999,'/redis.sentinel1.port','26379'),
    (999,'/redis.sentinel2.host','192.168.2.217'),
    (999,'/redis.sentinel2.port','26379'),
    (999,'/maxIdle','500'),
    (999,'/maxTotal','500'),
    (999,'/maxWaitTime','1000'),
    (999,'/testOnBorrow','true'),
    (999,'/minIdle','20'),
    ###################################################################
    #short message setting
    (999,'/messageGateWayAddr','http://192.168.2.221/mt'),
    (999,'/messageUserName','600459'),
    (999,'/messageUserpwd','600459'),
    (999,'/messageEncoding','GBK'),
    ###################################################################
    #sms mq config
    (999,'/rabbit.sms.hosts','192.168.2.215'),
    (999,'/rabbit.sms.username','hzx_admin'),
    (999,'/rabbit.sms.password','123456'),
    (999,'/rabbit.sms.port','5672'),
    (999,'/rabbit.sms.sessionCacheSize','5'),
    (999,'/rabbit.sms.virtualHost','3'),
    (999,'/rabbit.sms.exchange','myRoute'),
    (999,'/rabbit.sms.queue','smsQueue'),
    (999,'/rabbit.sms.routingKey','smsQueue'),
    ###################################################################
    #direct rabbitmq config
    (999,'/rabbit.direct.hosts','192.168.2.215'),
    (999,'/rabbit.direct.username','hzx_admin'),
    (999,'/rabbit.direct.password','123456'),
    (999,'/rabbit.direct.port','5672'),
    (999,'/rabbit.direct.sessionCacheSize','5'),
    (999,'/rabbit.direct.virtualHost','3'),
    (999,'/rabbit.direct.exchange','myRoute'),
    (999,'/rabbit.direct.queue','myRoute'),
    (999,'/rabbit.direct.routingKey','myRoute'),
    ###################################################################
    #fanout rabbitmq config
    (999,'/rabbit.fanout.hosts','192.168.2.215'),
    (999,'/rabbit.fanout.username','hzx_admin'),
    (999,'/rabbit.fanout.password','123456'),
    (999,'/rabbit.fanout.port','5672'),
    (999,'/rabbit.fanout.sessionCacheSize','5'),
    (999,'/rabbit.fanout.exchange','myRoute'),
    (999,'/rabbit.fanout.queue','myRoute'),
    ###################################################################
    #topic rabbitmq config
    (999,'/rabbit.topic.hosts','192.168.2.215'),
    (999,'/rabbit.topic.username','hzx_admin'),
    (999,'/rabbit.topic.password','123456'),
    (999,'/rabbit.topic.port','5672'),
    (999,'/rabbit.topic.sessionCacheSize','5'),
    (999,'/rabbit.topic.exchange','amq.topic'),
    (999,'/rabbit.topic.queue','passportTopic'),
    (999,'/rabbit.topic.message','top.message'),
    (999,'/rabbit.topic.message_pattern','topic.#'),
    ###################################################################
    #jiguang jpush setting
    (999,'/jpush.app_key','b3f4da2a44bd70d85ec3be2f'),
    (999,'/jpush.master_secret','9a53777497f796f050c86371'),
    (999,'/jpush.extra_key','type'),
    (999,'/jpush.product','false'),
    ###################################################################
    #hua xin https short message setting
    (999,'/huaxin.message.url','https://dx.ipyy.net/sms.aspx'),
    (999,'/huaxin.message.accountName','AA00586'),
    (999,'/huaxin.message.password','AA0058655'),
    (999,'/huaxin.message.Encoding','UTF-8'),
    ###################################################################
    # scmcc cmpp socket setting
    (999,'/scmcc.spId','923234'),
    (999,'/scmcc.sharedSecret','@6xYsjd2'),
    (999,'/scmcc.ismgIp','211.137.85.104'),
    (999,'/scmcc.ismgPort','7915'),
    (999,'/scmcc.spCode','10658568'),
    (999,'/scmcc.serviceId','-XYSJD'),
    (999,'/scmcc.timeOut','6000'),
    (999,'/scmcc.connectCount','3'),
    ###################################################################
    # jianyou cmpp  socket setting
    (999,'/jianyou.spId','923234'),
    (999,'/jianyou.sharedSecret','@6xYsjd2'),
    (999,'/jianyou.ismgIp','211.137.85.104'),
    (999,'/jianyou.ismgPort','7915'),
    (999,'/jianyou.spCode','10658568'),
    (999,'/jianyou.serviceId','-XYSJD'),
    (999,'/jianyou.timeOut','6000'),
    (999,'/jianyou.connectCount','3'),
    ###################################################################
    #ics sjd settting
    (999,'/ics.request.jks.sjd','D:/asserts/rbc_private_online.jks'),
    (999,'/ics.request.cer.sjd','D:/asserts/xy_bhe_public_online.cer'),
    (999,'/ics.request.url.sjd','https://m.139xy.cn:8181/himlp/xybhe?orgId=100000000002'),
    (999,'/ics.request.pwd.sjd','Rbc@xyrbc_2016'),
    (999,'/ics.request.alias.sjd','key_rbc')
    ]
    ## jiang xu
    print "delete path:"
    pathList_jx=sorted(pathList, key=lambda x:x[0],reverse=True)
    for path in pathList_jx:
      print path
      if path[0]==999:
        if src_host.exists(str(path_p)+path[1]):
          src_host.delete(str(path_p)+path[1])
      if path[0]!=999:
         if src_host.exists(path[1]):
            src_host.delete(path[1])
    
    print "create path:"
    ## sheng xu
    pathList_sx=sorted(pathList, key=lambda x:x[0],reverse=False)
    for path in pathList_sx:
      print path
      if path[0]!=999:
         if not src_host.exists(path[1]):
	    print path[1]
            src_host.create(path[1], path[2])
      if path[0]==999:
        if not src_host.exists(str(path_p)+path[1]):
	  print path[1]
          src_host.create(str(path_p)+path[1], path[2])
    print "view path and value:"
    zk_child_arr=src_host.get_children(path_p,None) 
    for pp in zk_child_arr:
      print "["+pp+"]"
      print src_host.get(path_p+'/'+pp,None)
      
    src_host.stop()

    print " ----------done-------------------"


if __name__ == "__main__":
    operate_zk_node('192.168.2.228:2181,192.168.2.229:2181,192.168.2.227:2181')
