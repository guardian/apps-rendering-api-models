#!/bin/bash

set -e

VERSION=$(scripts/get-version.sh)
sbt "project tsApiModels" "releaseNpm $VERSION"