#!/usr/bin/env bash

. ./cs-setup.sh

log "*******************************************************"
log "************************* dummy.sh ********************"
log "*******************************************************"

env | log_filter

log "Sleeping for 100s"
sleep 100