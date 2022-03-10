#!/bin/bash

usage() {
    echo "Usage: generate.sh <data-path> <maven|gradle>"
    exit 1
}

main() {

    path=$1
    tool=$2

    if [ -z "$path" ]; then
        usage
    fi

    if [ "$2" == "maven" ]; then
        cp -r $path/maven/ .
    elif [ "$2" == "gradle" ]; then
        cp -r $path/gradle/ .
    else
        usage
    fi
}

if [ $# -ne 2 ]; then
    usage
else
    main $@
fi
