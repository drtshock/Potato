#!/bin/bash

PREPARE_TYPE="--nonvegan"

if ! [ -z $1 ]; then
    if [[ $1 == "-h" || $1 == "?" ]]; then
        # Print usage
        echo "Usage: $0 <prepare type>"
        echo "Example: $0 --vegan"
        exit 0
    else
        PREPARE_TYPE=$1
    fi
fi
# Check if Docker is installed
if ! [ -x "$(command -v docker)" ]; then
    echo "You need to install Docker."
    exit 1
else
    echo "Running Potato with argument: \"$PREPARE_TYPE\""
fi

# Perform the build
docker run -it --name potato-in-a-container --rm -e VEGAN=${PREPARE_TYPE} potato:latest