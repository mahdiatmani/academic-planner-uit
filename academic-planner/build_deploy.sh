#!/bin/bash

server="/home/anas/Desktop/0/jboss-eap-7.2.0/jboss-eap-7.2";
project="/home/anas/Desktop/academic-planner/academic-planner";


#cd ${project} || exit;
#mvn -f pom.xml clean install;

#rm -rf ${server}/standalone/deployments/* || exit;

cp ${project}/target/academic-planner.war ${server}/standalone/deployments/;

bash ${server}/bin/standalone.sh;
tail -f -n 500 ${server}/standalone/log/server.log;