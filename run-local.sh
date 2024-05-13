#!/bin/bash

declare dc_infra=infra/compose.dev.yaml
declare dc_app=infra/compose-app.dev.yaml

function build_app() {
    cd ecommerce-monolith
    ./mvnw quarkus:image-build -DskipTests
    cd ..
}

function start_infra() {
    echo "Starting infra..."
    docker-compose -f $dc_infra up -d
}

function stop_infra() {
    echo "Stopping infra..."
    docker-compose -f $dc_infra stop
}

function start() {
    echo "Starting all services..."
    build_app
    docker-compose -f $dc_infra -f $dc_app up -d
    docker-compose -f $dc_infra -f $dc_app logs -f
}

function stop() {
    echo "Stopping all services..."
    docker-compose -f $dc_infra -f $dc_app stop
    docker-compose -f $dc_infra -f $dc_app rm -f
}

function restart() {
    stop
    sleep 5
    start
}

action=start

if [ "$1" == "start" ]; then
    action=start
elif [ "$1" == "stop" ]; then
    action=stop
elif [ "$1" == "restart" ]; then
    action=restart
fi

eval $action