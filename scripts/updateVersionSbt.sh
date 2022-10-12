#!/bin/bash

VERSION=$(./scripts/getVersion.sh)

echo "ThisBuild / version := \"$VERSION\"" > ./models/scala/version.sbt