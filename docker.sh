#!/bin/sh

# simple shell script to build and run the app as a docker container
# from https://www.baeldung.com/spring-boot-docker-start-with-profile

# set up mariadb

docker pull mariadb
docker images

export DB_CONTAINER_NAME=mariadb-server
export DB_FILES_ROOT_DIR=/var/lib/mysql

sudo mkdir $DB_FILES_ROOT_DIR -p
sudo chown -R $USER:$USER $DB_FILES_ROOT_DIR $DB_FILES_ROOT_DIR
docker run -d --name $DB_CONTAINER_NAME -p 3306:3306 -v /var/lib/mysql:/var/lib/mysql -e "MYSQL_ROOT_PASSWORD=ktjpheaven" mariadb
ls -la $DB_FILES_ROOT_DIR
docker ps
docker update --restart always $DB_CONTAINER_NAME
docker logs $DB_CONTAINER_NAME
# docker stop $DB_CONTAINER_NAME
# docker start $DB_CONTAINER_NAME

docker build --tag=kt-journal-prompt:latest .
docker run -e "SPRING_PROFILES_ACTIVE=mariadb" docker-with-spring-profile:latest
