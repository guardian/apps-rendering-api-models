#!/bin/bash -e

# The first argument to this script should be the version to check
VERSION=$1

if [[ -z "$VERSION" ]]
then
    echo "Please call this script with a version to check: ./version-exists-sonatype.sh 1.1.1"
    exit 1
fi

# Get just the http status from the request
HTTP_STATUS=$(curl -s -o /dev/null -I -w "%{http_code}" https://oss.sonatype.org/service/local/repositories/releases/content/com/gu/apps-rendering-api-models_2.12/$VERSION/)

# 404 is our failure state, as the version wasn't found so it hasn't been released yet
if [[ $HTTP_STATUS == 404 ]]
then
    echo "Version $VERSION does not exist in Sonatype"
    exit 1
else
    echo "Version $VERSION already exists in Sonatype"
    exit 0
fi
