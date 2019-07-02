# FileConsumer

Java application that will consume a CSV file, parse the data and insert to a SQLite database.

## Getting Started

```
git clone https://github.com/MR-D05/FileConsumer.git
```

### Prerequisites

Eclipse, Maven, sqlite-jdbc

### Installing

You can simply clone the project and import it into Eclipse, but if you are building the project from the ground up, you will need to setup a Maven project in Eclipse and copy the following contents into your pom.xml:

Say what the step will be

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>broadstone</groupId>
    <artifactId>broadstone</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.7.2</version>
        </dependency>
    </dependencies>
</project>
```
## Built With

* [Eclipse](https://www.eclipse.org) - IDE
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Ben Broadstone** - [MR-D05](https://github.com/MR-D05)
