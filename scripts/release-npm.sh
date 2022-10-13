#!/bin/bash

set -e
SNAPSHOT=false
if [[ "$1" == "snapshot" ]] ; then
    SNAPSHOT=true
fi

VERSION=$(scripts/get-version.sh)

if $SNAPSHOT ; then
    sbt "project tsApiModels" "set scroogeTypescriptPublishTag := \"snapshot\"" "releaseNpm $VERSION"
else
    sbt "project tsApiModels" "releaseNpm $VERSION"
fi