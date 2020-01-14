#!/bin/bash

argc=$#
argv0=$0
argv1=$1
if [ 1 -ne $argc ];then
	echo "arguments error : $argc"
	exit 0
fi

#source /etc/profile
java -server -Dspring.profiles.active=프로퍼티 -jar /app/.../$argv1 > /dev/null 2>&1 &

exit 0