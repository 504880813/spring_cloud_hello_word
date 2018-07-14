#!/bin/sh

## java env
export JAVA_HOME=/usr/local/java/jdk1.7.0_79
export JRE_HOME=$JAVA_HOME/jre



## you just need to change this param name
SERVICE_NAME=mbp-im-server-service

SERVICE_DIR=/home/mbp/service/mbp-im-server

JAR_NAME=$SERVICE_NAME\.jar
PID=$SERVICE_NAME\.pid

cd $SERVICE_DIR
LOGS_DIR=/home/mbp/logs/mbp-im
if [ ! -d $LOGS_DIR ]; then
	mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/mbp-im-server-service.log


case "$1" in

    start)
        nohup $JRE_HOME/bin/java -server -Xms64M -Xmx64M  -Xss512k -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:PermSize=64M -XX:MaxPermSize=128M -XX:+DisableExplicitGC -XX:MaxTenuringThreshold=31 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC  -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m  -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -Djava.awt.headless=true -Duser.timezone=GMT+8 -jar $JAR_NAME  >$STDOUT_FILE 2>&1 &
        echo $! > $SERVICE_DIR/$PID
        echo " ……………………………………………………………………………………"
		echo ""
		echo "╭⌒╮成功━┅~ ¤　 ╭⌒╮ ╭⌒╮ "
		echo "╭⌒╭⌒╮╭⌒╮～╭⌒╮︶︶,　︶︶ "
		echo ",︶︶︶︶,''︶~~ ,''~︶︶　 ,'' "
		echo "╱◥█◣　╱◥█◣ "
		echo "︱田︱田︱︱田︱田︱ "
		echo "╬╬╬╬╬╬╬╬╬╬╬╬╬╬ "
		echo ""
		echo "……………………………………………………………………………………"
        ;;

    stop)
        kill `cat $SERVICE_DIR/$PID`
        rm -rf $SERVICE_DIR/$PID
        echo "********************* stop $SERVICE_NAME  ******************"

        sleep 5
        P_ID=`ps -ef | grep -w "$SERVICE_NAME" | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "********************* $SERVICE_NAME process not exists or stop success"
        else
            echo "********************* $SERVICE_NAME process pid is:$P_ID"
		    echo "		    	              .======."
		    echo "		    	              | INRI |"
			echo "				              |      |"
			echo "				     .========'      '========."
			echo "				     |       $SERVICE_NAME    |"
			echo "				     '========. \   / ========'"
			echo "				              | |  / |"
			echo "				              |/-.(  |"
			echo "				              |\_._\ |"
			echo "				              | \ \ ;|"
			echo "				              |  > |/|"
			echo "				              | / // |"
			echo "				              | |//  |"
			echo "				              | \(\  |"
			echo "				              |      |"
			echo "				              |      |"
			echo "				  \\    _  _\\| \//  |//_   _ \// _"
            kill -9 $P_ID
        fi
        ;;

    restart)
        $0 stop
        sleep 2
        $0 start
        echo "*********************restart $SERVICE_NAME"
        ;;

    *)
        ## restart
        $0 stop
        sleep 2
        $0 start
        ;;
esac
exit 0

