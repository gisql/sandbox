#!/bin/bash -e

. ./cs-setup.sh

/usr/bin/consul-template -consul consul.service.consul:8500 -once -template \
    '/logging-aws.xml.ctmpl:/logging-aws.xml:cat /logging-aws.xml' | log_filter &

/usr/bin/envconsul -consul consul.service.consul:8500 -prefix config -prefix service/sandbox/config \
    /usr/bin/java -Dlogback.configurationFile=/logging-aws.xml -jar /sandbox.jar
