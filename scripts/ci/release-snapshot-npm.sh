#!/bin/bash
set -e

pushd $(dirname $0)
VERSION=$(./get-version.sh)
popd 

# If we are in CI and this is a snapshot release, we should get the version from the tag created in the GitHub Release
if [[ $CI ]] ; then
    VERSION=$(git describe --tags)
fi

sbt "project tsApiModels" "set scroogeTypescriptPublishTag := \"snapshot\"" "releaseNpm $VERSION"