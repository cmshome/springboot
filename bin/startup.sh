#!/bin/sh

java -jar -Xms2048m -Xmx2048m ./springboot-1.0-SNAPSHOT.jar com.lxk.springboot.http.Application  --spring.config.location=file:application.properties > log.file 2>&1 &
