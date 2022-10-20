#!/bin/bash

set -e

VERSION=$(scripts/get-version.sh)
SNAPSHOT=false

if [[ "$1" == "snapshot" ]] ; then
    SNAPSHOT=true
    # If we are in CI and this is a snapshot release, we should get the version from the tag created in the GitHub Release
    if [[ $CI ]] ; then
        VERSION=$(git describe --tags)
    fi
fi


if $SNAPSHOT ; then
    sbt "project tsApiModels" "set scroogeTypescriptPublishTag := \"snapshot\"" "releaseNpm $VERSION"
else
    sbt "project tsApiModels" "releaseNpm $VERSION"
fi