#!/bin/sh

TAG=latest

if [ $1 ] then
    TAG=$1
fi

# Perform the build
docker build -t potato:$TAG  -f Dockerfile.spud
