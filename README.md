# Kotlin Journal Prompt Server (KtJournalPrompts)

## Summary

This is a simple Spring Boot REST server that provides an API to create and read Journal Prompts.

These Journal Prompts can help you to write your own journal entries. they provide ideas for what to write about in your
journal.

## Setup

The server currently has two database configuration profiles:

* **`H2`**
* **`MariaDb`**

These can be selected between by using the `SPRING_PROFILES_ACTIVE` environment variable.

i.e:

```shell
SPRING_PROFILES_ACTIVE=mariadb
```

or

```shell
SPRING_PROFILES_ACTIVE=h2
```

### H2

if you choose to use H2 as the database a new file will be created called **`KtJournalPrompts.h2`** to store the prompt
data.`

### MariaDb

To use MariaDb I have run a docker container using the following commands from a wsl terminal.

1. Create a directory for the database to store persistent data and set it's user so that we can execute mariadb without
   needing root access:

```shell
export DB_FILES_ROOT_DIR=/var/lib/mysql

sudo mkdir $DB_FILES_ROOT_DIR -p
sudo chown -R $USER:$USER $DB_FILES_ROOT_DIR $DB_FILES_ROOT_DIR
```

2. Run the MariaDb docker container:

```shell
export DB_CONTAINER_NAME=mariadb-server

docker run -d --name $DB_CONTAINER_NAME -p 3306:3306 -v /var/lib/mysql:/var/lib/mysql -e "MYSQL_ROOT_PASSWORD=ktjpheaven" mariadb
```

### **If you intend to run this on a public server you will need to change the passwords used.**

I used [this guide](https://techexpert.tips/mariadb/mariadb-docker-installation/) to set up MaridDb in a docker
container.
