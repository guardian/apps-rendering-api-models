#!/bin/bash -e

pushd $(dirname $0)
VERSION=$(./get-version.sh)
popd

# If we are in CI and this is a snapshot release, we should get the version from the tag created in the GitHub Release
if [[ $CI ]] ; then
    VERSION=$(git describe --tags)
fi

# Strip a leading "v" character, so "v0.0.0 => 0.0.0"
if [[ ${VERSION:0:1} == "v" ]] ; then
    VERSION=${VERSION:1}
fi

# sbt-release uses -SNAPSHOT suffix to make the release to the SNAPSHOT channel
if [[ ${VERSION: -9} != "-SNAPSHOT" ]] ; then
    echo "Version must end in -SNAPSHOT. Adding -SNAPSHOT suffix"
    VERSION="$VERSION-SNAPSHOT"
fi

echo "Releasing version $VERSION Sonatype as snapshot"
sbt "clean" "project scalaApiModels" "set isSnapshot := true" "release release-version $VERSION with-defaults"
