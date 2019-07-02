# FileConsumer

Java application that will consume a CSV file, parse the data and insert to a SQLite database.

## Getting Started

Clone the repository:

```
git clone https://github.com/MR-D05/FileConsumer.git
```

Open Eclipse, and right-click in the Package Explorer, and choose "import".

Expand the Maven tab, and choose "Existing Maven Projects". 

Choose "Browse" and navigate to where you cloned the repository. Click "Finish".

From here you can simply run the project by clicking the green arrow at the top of your Eclipse browser. 

In the console, enter the name of the input file "ms3Interview.csv" when prompted. 

### Prerequisites

Eclipse, Maven, sqlite-jdbc

### Installing

You can simply clone the project and import it into Eclipse, but if you are building the project from the ground up, you will need to setup a Maven project in Eclipse and copy the following contents into your pom.xml:

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
### Experience

I ended up using a non-transient SQLite database so that I could reference the table as I was unsure whether it was possible to do so using an in-memory databse. There was some back and forth troubleshooting the database connection, and in the end I used prepared statements to enter the data into the database. I got a bit tripped up on one of the caveats of the project to use double quotes on entries that use commas, I think my implementation accounts for this. I used BufferedReader to iterate over the file and PrintWriter to write to a log file the bad entries.  

## Built With

* [Eclipse](https://www.eclipse.org) - IDE
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Ben Broadstone** - [MR-D05](https://github.com/MR-D05)
