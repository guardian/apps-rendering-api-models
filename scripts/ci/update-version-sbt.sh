#!/bin/bash

VERSION=$(./scripts/get-version.sh)

echo "ThisBuild / version := \"$VERSION\"" > ./models/scala/version.sbt