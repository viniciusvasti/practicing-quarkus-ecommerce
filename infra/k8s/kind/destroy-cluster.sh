#!/bin/sh

echo "Destroying Kubernetes cluster..."

kind delete cluster --name quarkus-ecommerce-k8s-cluster