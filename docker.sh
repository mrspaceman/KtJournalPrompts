#!/bin/sh

# simple shell script to build and run the app as a docker container
# from https://www.baeldung.com/spring-boot-docker-start-with-profile

# set up mariadb

docker pull mariadb
docker images

export DB_CONTAINER_NAME=mariadb-server
export DB_FILES_ROOT_DIR=/var/lib/mysql

docker network create prompts-net

sudo mkdir $DB_FILES_ROOT_DIR -p
sudo chown -R "$USER":"$USER" $DB_FILES_ROOT_DIR $DB_FILES_ROOT_DIR
docker run -d --net prompts-net --name $DB_CONTAINER_NAME -p 3306:3306 -v /var/lib/mysql:/var/lib/mysql -e "MYSQL_ROOT_PASSWORD=ktjpheaven" mariadb
ls -la $DB_FILES_ROOT_DIR
docker ps
docker update --restart always $DB_CONTAINER_NAME
docker logs $DB_CONTAINER_NAME
# docker stop $DB_CONTAINER_NAME
# docker start $DB_CONTAINER_NAME



unset SPRING_PROFILES_ACTIVE && mvn clean package
docker rm -f kt-journal-prompt && docker rmi -f kt-journal-prompt
clear;docker build --tag=kt-journal-prompt:latest . --progress=plain
docker run -d --net prompts-net -e "SPRING_PROFILES_ACTIVE=mariadb" -p 8080:8080 kt-journal-prompt:latest

docker inspect $DB_CONTAINER_NAME | grep IPAddress
docker inspect kt-journal-prompt | grep IPAddress
docker network inspect prompts-net

docker logs kt-journal-prompt
docker exec -it kt-journal-prompt bash
