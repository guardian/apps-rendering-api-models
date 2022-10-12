#!/bin/bash

VERSION=$(./scripts/getVersion.sh)

sbt "project tsApiModels" "releaseNpm $VERSION"