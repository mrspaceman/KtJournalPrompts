#!/bin/sh

# simple shell script to build and run the app as a docker container
# from https://www.baeldung.com/spring-boot-docker-start-with-profile

# set up mariadb

docker pull mariadb
docker pull postgres
docker images

export MYSQL_CONTAINER_NAME=mariadb-server
export MYSQL_FILES_ROOT_DIR=/var/lib/mysql

export POSTGRESQL_CONTAINER_NAME=postgresql-server
export POSTGRESQL_FILES_ROOT_DIR=/var/lib/postgresql

docker network create prompts-net

sudo mkdir $POSTGRESQL_FILES_ROOT_DIR -p
sudo mkdir $MYSQL_FILES_ROOT_DIR -p
sudo chown -R "$USER":"$USER" $POSTGRESQL_FILES_ROOT_DIR $MYSQL_FILES_ROOT_DIR

docker run -d --net prompts-net --name POSTGRESQL_CONTAINER_NAME -p 5432:5432 -v /var/lib/postgresql/data:/var/lib/postgresql -e "POSTGRES_PASSWORD=ktjpheaven" postgres
docker run -d --net prompts-net --name $MYSQL_CONTAINER_NAME -p 3306:3306 -v /var/lib/mysql:/var/lib/mysql -e "MYSQL_ROOT_PASSWORD=ktjpheaven" mariadb

ls -la $MYSQL_FILES_ROOT_DIR
docker ps
docker update --restart always $MYSQL_CONTAINER_NAME
docker logs $MYSQL_CONTAINER_NAME
# docker stop $MYSQL_CONTAINER_NAME
# docker start $MYSQL_CONTAINER_NAME



unset SPRING_PROFILES_ACTIVE && mvn clean package
docker rm -f kt-journal-prompt && docker rmi -f kt-journal-prompt
clear;docker build --tag=kt-journal-prompt:latest . --progress=plain
docker run -d --net prompts-net -e "SPRING_PROFILES_ACTIVE=mariadb" -p 8080:8080 kt-journal-prompt:latest

docker inspect $MYSQL_CONTAINER_NAME | grep IPAddress
docker inspect kt-journal-prompt | grep IPAddress
docker network inspect prompts-net

docker logs kt-journal-prompt
docker exec -it kt-journal-prompt bash
