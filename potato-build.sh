#!/bin/bash

TAG=latest

if ! [ -z $1 ]; then
    if [[ $1 == "-h" || $1 == "?" ]]; then
        # Print usage
        echo "Usage: $0 <tag>"
        echo "Example: $0 1.0.0"
        exit 0
    else
        TAG=$1
    fi
fi

echo "Using tag: '$TAG'"

# Perform the build
docker build -t potato:$TAG -f Dockerfile.spud .
