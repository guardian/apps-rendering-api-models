#!/bin/bash

set -e

npm config set ignore-scripts true

pushd $(dirname $0)
VERSION=$(./get-version.sh)
popd

if npm view "@guardian/apps-rendering-api-models@$VERSION" > /dev/null 2>&1 ; then
    echo "$VERSION already released. Exiting"
    exit 0
else
    echo "$VERSION not yet released. Releasing"
    sbt "project tsApiModels" "releaseNpm $VERSION"

    git tag "v$VERSION" -m "$VERSION"
    # Print "New tag:" so changesets picks up the release and creates a GitHub Release
    echo "New tag: $VERSION"
fi