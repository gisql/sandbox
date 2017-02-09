#!/bin/bash -e

/usr/bin/envconsul -consul consul.service.consul:8500 -prefix service/sandbox/logging \
    /bin/bash dummy.sh &

/usr/bin/envconsul -consul consul.service.consul:8500 -prefix config -prefix service/sandbox/config \
    /usr/bin/java -Dlogback.configurationFile=/logging-aws.xml -jar /sandbox.jar
