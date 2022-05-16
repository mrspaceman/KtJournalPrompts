# Kotlin Journal Prompt Server (KtJournalPrompts)

## Summary

This is a simple Spring Boot REST server that provides an API to create and read Journal Prompts.

These Journal Prompts can help you to write your own journal entries. they provide ideas for what to write about in your
journal.

## Setup

The server currently has two database configuration profiles:

* **`H2`**
* **`MariaDb`**

To user MariaDb I have run a docker container using the following commands from a wsl terminal:

```shell

export DB_CONTAINER_NAME=mariadb-server
export DB_FILES_ROOT_DIR=/var/lib/mysql

sudo mkdir $DB_FILES_ROOT_DIR -p
sudo chown -R $USER:$USER $DB_FILES_ROOT_DIR $DB_FILES_ROOT_DIR
docker run -d --name $DB_CONTAINER_NAME -p 3306:3306 -v /var/lib/mysql:/var/lib/mysql -e "MYSQL_ROOT_PASSWORD=ktjpheaven" mariadb
```

### **If you intend to run this on a public server you will need to change the passwords used.**
