#!/usr/bin/env bash

APP="red-bull"

PID=$(jps | grep $APP | awk '{print $1}')
if [[ $PID ]]; then
  kill -9 "$PID"
  sleep 3
  echo "$APP has stopped"
  exit 0
else
  echo "$APP isn't running"
  exit 1
fi
