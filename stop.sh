#!/bin/bash

argc=$#
argv0=$0
argv1=$1
if [ 1 -ne $argc ];then
        echo "arguments error : $argc"
        exit 0
fi

#ps -ef | grep $argv1 | grep -v grep | grep -v bash | awk '{print $2}' | xargs kill -9
PID=`ps -ef | grep $argv1 | grep -v grep | grep -v bash  | awk '{print $2}'`
if [ $PID ]; then
	kill -15 $PID
	while kill -0 $PID > /dev/null 2>&1
	do
		sleep 1
	done
	echo STOPPED -$argv1-
	echo STOPPED -$PID-
else
	echo 'not working'
fi

exit 0