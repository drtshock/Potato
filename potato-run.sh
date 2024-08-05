#!/bin/bash

# HOW TO RUN THE POTATO IMAGE
#  1. Run this script and pass in an optional prepare type argument
#     e.g. ./potato-run.sh "--vegan"
#  2. You can run using docker compose run
#     e.g. docker compose run --build potato (builds the container before running)
#     e.g. docker compose run potato (if you already built it)
#     e.g. docker compose run potato --rm (removes the container upon exit)
#  2a. To pass in an argument, either edit the .env file, 
#      or set a local environment variable: e.g. export VEGAN="--vegan"
#  3. You can run using docker compose up and passing in the --build arg
#     e.g. docker compose up --build
#  3a. Make sure to clean up containers since compose up doesn't have a --rm option
#     e.g. docker container rm potato-potato-1


# Preparation type for the built potato container
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
