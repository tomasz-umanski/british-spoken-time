# British Spoken Time Converter

A Java application that converts time from 24-hour format (both HH:MM and H:MM) to spoken British English.

---

## Features (Assumptions)

### Processing

- **Interactive CLI Mode**: Prompts for entering times
- **Command-Line Arguments**: Batch processing of multiple times
- **24-Hour Time Format**: Accepts 24-hour format as `HH:MM` or `H:MM` (e.g., `09:30` or `9:30`)

### British Conversion Rules

#### AM/PM indicators

- Automatically added based on 24-hour input (e.g., `07:30` - `AM`, `19:30` - `PM`)

#### Exact Hours

- Exact hour times include period indicators:
- `1:00` - `one o'clock AM`

#### Minutes 1-30 ("Past" Format)

- Uses "past" construction for the first half-hour:
- `7:15` - `quarter past seven AM`

#### Minutes 31-39 ("Digital" Format)

- Spoken as digits (excludes 35 minutes):
- `6:32` - `six thirty two AM`

#### Minutes 35, 40-59 ("To" Format)

- Uses "to" construction referencing the next hour:
- `7:35` - `twenty five to eight AM`

#### Special Time Phrases

- **Quarter Times**: 15 and 45 minutes use `quarter past/to`
- **Half Hour**: 30 minutes use `half past`
- **Noon**: 12:00 converts to `noon` (no AM/PM indicator)
- **Midnight**: 00:00 converts to `midnight` (no AM/PM indicator)

---

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

---

## Quick Start

1. Clone and build:
   ```bash
   git clone git@github.com:tomasz-umanski/british-spoken-time.git
   cd british-spoken-time
   mvn clean package
   ```
2. This will create `british-spoken-time-1.0-SNAPSHOT.jar` in the `target` directory of a project, that can be run with:
   ```bash
   # Interactive mode
   java -jar target/british-spoken-time-1.0-SNAPSHOT.jar
   
   # Command line arguments
   java -jar target/british-spoken-time-1.0-SNAPSHOT.jar "3:00" "07:30" "11:45"
   ```

---

## Building the Project

From the project root directory:

### Compile source code

```bash
mvn clean compile
```

### Build executable JAR

```bash
mvn clean package
```

Creates `target/british-spoken-time-1.0-SNAPSHOT.jar`

### Run tests

```bash
mvn test
```

---

## Usage

From the project root directory:

### Method 1: JAR File

```bash
# Interactive mode
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar

# Single time conversion
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar "14:30"

# Multiple time conversions
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar "9:00" "12:15" "17:45"
```

### Method 2: Maven Execution

```bash
# Interactive mode
mvn exec:java -Dexec.mainClass="pl.umanski.Main"

# Single time conversion
mvn exec:java -Dexec.mainClass="pl.umanski.Main" -Dexec.args="14:30"

# Multiple time conversions
mvn exec:java -Dexec.mainClass="pl.umanski.Main" -Dexec.args="9:00 12:15 17:45"
```

---

## Example Conversions

| Input | Output                |
|-------|-----------------------|
| 09:00 | nine o'clock AM       |
| 19:15 | quarter past seven PM |
| 14:30 | half past two PM      |
| 12:00 | noon                  |
| 00:00 | midnight              |
