#!/bin/bash

# HOW TO BUILD THE POTATO IMAGE
#  1. Run this script and pass in an optional tag for the container
#     e.g. ./potato-build.sh 1.0.0
#  2. Alternatively, you can can run using docker compose
#     e.g. docker compose run --build potato
#  2a. To pass in an argument, either edit the .env file, 
#      or set a local environment variable: e.g. export VEGAN="--vegan"

# Tag for the built container
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
docker build -t potato:$TAG -f Dockerfile.spud .
