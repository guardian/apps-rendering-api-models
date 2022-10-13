#!/bin/bash

VERSION=$(./scripts/get-version.sh)

sbt "project tsApiModels" "releaseNpm $VERSION"