#!/bin/sh

PREPARE_TYPE="--nonvegan"

if [ $1 ]
then
    TAGPREPARE_TYPE=$1
fi

# Perform the build
docker run -it --name potato-in-a-container --rm -e VEGAN=$PREPARE_TYPE potato:latest
