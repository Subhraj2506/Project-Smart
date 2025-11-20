#!/usr/bin/env bash
set -e
# If Railway style DATABASE_URL is present, convert it to SPRING envs
if [ -n "$DATABASE_URL" ] && [ -z "$SPRING_DATASOURCE_URL" ]; then
  # DATABASE_URL=postgres://user:pass@host:port/dbname
  url=${DATABASE_URL#postgres://}
  userpass=${url%%@*}
  hostportdb=${url#*@}
  user=${userpass%%:*}
  pass=${userpass#*:}
  hostport=${hostportdb%%/*}
  host=${hostport%%:*}
  port=${hostport#*:}
  db=${hostportdb#*/}
  export SPRING_DATASOURCE_URL="jdbc:postgresql://${host}:${port}/${db}"
  export SPRING_DATASOURCE_USERNAME=${user}
  export SPRING_DATASOURCE_PASSWORD=${pass}
fi
exec java -jar /app/app.jar
