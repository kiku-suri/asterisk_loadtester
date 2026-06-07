#!/bin/bash

set -e

BASE_DIR=/opt/loadtester

echo "Creating folder structure..."

mkdir -p $BASE_DIR

mkdir -p $BASE_DIR/app
mkdir -p $BASE_DIR/config

mkdir -p $BASE_DIR/logs
mkdir -p $BASE_DIR/logs/application
mkdir -p $BASE_DIR/logs/jobs
mkdir -p $BASE_DIR/logs/ssh
mkdir -p $BASE_DIR/logs/metrics

mkdir -p $BASE_DIR/reports
mkdir -p $BASE_DIR/reports/csv
mkdir -p $BASE_DIR/reports/archive

mkdir -p $BASE_DIR/uploads
mkdir -p $BASE_DIR/uploads/scenarios

mkdir -p $BASE_DIR/scripts

mkdir -p $BASE_DIR/source

mkdir -p $BASE_DIR/backups
mkdir -p $BASE_DIR/backups/database
mkdir -p $BASE_DIR/backups/config

mkdir -p $BASE_DIR/temp

mkdir -p $BASE_DIR/runtime
mkdir -p $BASE_DIR/runtime/jobs
mkdir -p $BASE_DIR/runtime/metrics

mkdir -p $BASE_DIR/security

echo "Folder structure created."

tree $BASE_DIR
