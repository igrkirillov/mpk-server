#!/bin/bash

export JAVA_HOME='/home/laba/Documents/jdk-11.0.7/'
mvn clean package
docker build -t mpk-server .