#!/bin/bash

set -e

echo "Configuring GPG"
scripts/ci/configure-gpg.sh

echo "Releasing to Sonatype"
scripts/ci/release-sonatype.sh

echo "Releasing to NPM"
scripts/ci/release-npm.sh