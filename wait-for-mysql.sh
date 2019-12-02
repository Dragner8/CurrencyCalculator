#!/usr/bin/env bash
HOST="$DATA_HOST"
PORT="$DATA_PORT"
USER="$SPRING_DATASOURCE_USERNAME"
PASS="$SPRING_DATASOURCE_PASSWORD"

echo "MySQL is unavailable - waiting"
until echo '\q' | mysql -h"$HOST" -P"$PORT" -u"$USER" -p"$PASS"; do
        sleep 3
done

echo "MySQL is ready"