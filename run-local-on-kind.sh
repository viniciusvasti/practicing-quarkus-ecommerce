#!/bin/bash

declare dc_infra=infra/compose.dev.yaml
declare dc_app=infra/compose-app.dev.yaml

function createCluster() {
    cd infra/k8s/kind
    ./create-cluster.sh
    cd ../../..
}

function applyManifests() {
    cd infra/k8s
    kubectl apply -f .
    cd ../..
}

function start() {
    echo "Starting all services..."
    createCluster
    applyManifests
}

function stop() {
    echo "Stopping all services..."
    cd infra/k8s/kind
    ./destroy-cluster.sh
    cd ../../..
}

action=start

if [ "$1" == "start" ]; then
    action=start
elif [ "$1" == "stop" ]; then
    action=stop
fi

eval $action