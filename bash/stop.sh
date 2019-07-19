#!/bin/bash
PID=$(cat ./.pid)
sudo kill -TERM $PID
echo "===app is stopped!==="
