#!/bin/bash

set -e

echo "=================================================="
echo "Installing Load Testing Orchestrator Server"
echo "=================================================="

apt update

apt install -y \
openjdk-17-jdk \
mysql-server \
maven \
git \
curl \
wget \
jq \
bc \
zip \
unzip \
screen \
tmux \
openssh-client \
openssh-server \
net-tools \
htop \
sysstat \
tcpdump \
ngrep \
iftop \
tree

echo "Creating Load Tester Directories..."

mkdir -p /opt/loadtester

mkdir -p /opt/loadtester/app
mkdir -p /opt/loadtester/config

mkdir -p /opt/loadtester/logs/application
mkdir -p /opt/loadtester/logs/jobs
mkdir -p /opt/loadtester/logs/ssh
mkdir -p /opt/loadtester/logs/metrics

mkdir -p /opt/loadtester/reports/csv
mkdir -p /opt/loadtester/reports/archive

mkdir -p /opt/loadtester/uploads/scenarios

mkdir -p /opt/loadtester/scripts

mkdir -p /opt/loadtester/runtime/jobs
mkdir -p /opt/loadtester/runtime/metrics

mkdir -p /opt/loadtester/security

mkdir -p /opt/loadtester/temp

mkdir -p /opt/loadtester/backups/database
mkdir -p /opt/loadtester/backups/config

mkdir -p /opt/loadtester/source

echo "Starting MySQL..."

systemctl enable mysql
systemctl start mysql

echo "Installation Completed"

echo ""
echo "Java Version:"
java -version

echo ""
echo "MySQL Version:"
mysql --version

echo ""
echo "Directory Structure:"
tree /opt/loadtester
