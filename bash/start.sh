#!/bin/bash
sh stop.sh

echo "current path is ------> $(pwd)"
APP_NAME=resume-boot.jar
export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8


#----------------------not modify----------------------------------------#
java -Duser.timezone=GMT+8 \
  -Xms512m -Xmx4096m -Xmn512m \
  -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=70 \
  -XX:+CMSParallelRemarkEnabled -XX:+CMSClassUnloadingEnabled -XX:SurvivorRatio=8 -XX:+DisableExplicitGC \
  -jar $APP_NAME >/dev/null &
echo $!> ./.pid
echo "===app started!==="
sleep 5
tail -100f logs/application.log
