#!/usr/bin/env bash

APP="red-bull"

echo "try to stop application"
bash stop.sh
echo "try to start application"

ROOT_PATH=$(dirname "$(pwd)")

if [[ ! -d $HOME/logs/$APP ]]; then
  mkdir -p "$HOME/logs/$APP"
fi

if [ -z "$RED_BULL_SALT" ]; then
    echo "environment variable \$RED_BULL_SALT not exist"
    exit 1
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
    -Dspring.profiles.active=prod \
    -Djasypt.encryptor.password="$RED_BULL_SALT" \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:10002 \
    -jar "$ROOT_PATH/$APP-0.0.1-SNAPSHOT.jar" >>"$HOME/logs/$APP/$APP.log" 2>&1 &
then
  echo "$APP is running, more logs: $HOME/logs/$APP/$APP.log"
else
  echo "$APP start failed, more logs: $HOME/logs/$APP/$APP.log"
fi
