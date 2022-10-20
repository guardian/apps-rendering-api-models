#!/bin/bash

pushd $(dirname $0)
VERSION=$(./get-version.sh)
popd

echo "ThisBuild / version := \"$VERSION\"" > ./models/scala/version.sbt