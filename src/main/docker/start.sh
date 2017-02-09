#!/bin/bash -e

/usr/bin/consul-template -consul consul.service.consul:8500 -template \
    '/logging-aws.xml.ctmpl:/logging-aws.xml:cat /logging-aws.xml' | java -jar /logger.jar &

/usr/bin/envconsul -consul consul.service.consul:8500 -prefix config -prefix service/sandbox/config \
    /usr/bin/java -Dlogback.configurationFile=/logging-aws.xml -jar /sandbox.jar
