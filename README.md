# British Spoken Time Converter

## Overview

Converts digital time in 12-hour format (both HH:MM and H:MM) to spoken British English.

The application supports two modes of operation:
- **Command-line arguments**: Pass the time directly as an argument for quick conversion
- **Interactive mode**: Run without arguments to enter times interactively through prompts

### Examples

- Input: "1:00" → Output: "one o'clock"
- Input: "12:00" → Output: "noon"
- Input: "7:30" → Output: "half past seven"
- Input: "9:45" → Output: "quarter to ten"
- Input: "6:32" → Output: "six thirty two"

## Prerequisites

Before you begin, ensure you have the following requirements met:

- **JDK 21:** Make sure Java Development Kit (JDK) version 21 is installed. You can verify by running the following
  command:

```bash
java -version
```

If not installed, you can download and install it
from [Oracle JDK 21](https://www.oracle.com/pl/java/technologies/downloads/#java21).

- **Apache Maven 3.8.x+:** Maven is required to manage project dependencies and build the application. Verify Maven
  installation by running:

```bash
mvn -v
```

If Maven is not installed, refer to [Maven Installation Guide](https://maven.apache.org/install.html).

## Building the Project

From the project root directory, run:

```bash
mvn clean compile
```

## Running tests

From the project root directory, run:

### Run all tests

```bash
mvn test
```

## Running the Application

### With maven

From the project root directory, run:

```bash
# Interactive mode
mvn exec:java -Dexec.mainClass="pl.umanski.Main"

# With command line arguments
mvn exec:java -Dexec.mainClass="pl.umanski.Main" -Dexec.args="4:30"
mvn exec:java -Dexec.mainClass="pl.umanski.Main" -Dexec.args="3:00 07:30 11:45"
```

### With JAR

First from the project root directory, build the JAR file:
```bash
mvn clean package
```

Then run with:
```bash
# Interactive mode (prompts for input)
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar

# With command line arguments
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar "4:30"
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar "3:00" "07:30" "11:45"
```
