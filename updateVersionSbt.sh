#!/bin/bash

VERSION=$(./getVersion.sh)

echo "ThisBuild / version := $VERSION" > ./models/scala/version.sbt