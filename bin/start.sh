#!/usr/bin/env bash

APP="red-bull"

if [[ $# -ne 1 ]]; then
  echo "start application need jasypt.encryptor.password"
  exit 1
fi

echo "try to stop application"
sh stop.sh
echo "try to start application"

ROOT_PATH=$(dirname "$(pwd)")

if [[ ! -d $HOME/logs/$APP ]]; then
  mkdir -p "$HOME/logs/$APP"
fi

if
  nohup java \
    -XX:+UseG1GC \
    -XX:MaxGCPauseMillis=200 \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath="$HOME/logs/$APP" \
    -XX:+UseStringDeduplication \
    -Xms128m \
    -Xmx128m \
    -Dloader.path="$ROOT_PATH/config" \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:10002 \
    -Djasypt.encryptor.password="$1" \
    -jar "$ROOT_PATH/red-bull-0.0.1-SNAPSHOT.jar" \
    --spring.profiles.active=prod >>"$HOME/logs/$APP/nohup.out" 2>&1 &
then
  echo "$APP is running, more logs: $HOME/logs/$APP/nohup.out"
else
  echo "$APP start failed, more logs: $HOME/logs/$APP/nohup.out"
fi
