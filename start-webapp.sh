#! /usr/bin/env bash

export JBOSS_HOME=~/workspace/servers/wildfly
PROJECT_HOME=~/workspace/week4-demo1

rm -rf $PROJECT_HOME/target
rm -rf $JBOSS_HOME/standalone/tmp
rm -rf $JBOSS_HOME/standalone/data
rm -rf $JBOSS_HOME/standalone/deployments/*.failed
rm -rf $JBOSS_HOME/standalone/deployments/*.war
rm -rf $JBOSS_HOME/standalone/deployments/*.dodeploy

cd $PROJECT_HOME
mvn clean install -Dmaven.test.skip=true -T 4C

if [ $? -gt 0 ]; then
	echo;
	read -p "Press [Enter] key to exit."
	exit 1;
fi


ln -s $PROJECT_HOME/target/week4-demo1.war $JBOSS_HOME/standalone/deployments/week4-demo1.war

$JBOSS_HOME/bin/standalone.sh -Ddevelopment.path=$PROJECT_HOME