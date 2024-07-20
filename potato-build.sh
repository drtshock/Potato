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

# Check if Docker is installed
if ! [ -x "$(command -v docker)" ]; then
    echo "You need to install Docker."
    exit 1
else
    echo "Using tag: '$TAG'"
fi

# Perform the build
# docker build -t potato:$TAG -f Dockerfile.spud .
