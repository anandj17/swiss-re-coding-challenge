# Big Company Analysis

This is a Java SE console application for analyzing organizational structure data from a CSV file.  
It validates salary policies and reporting line depth to help identify violations in the company's hierarchy.

---

## Features

- Checks that every manager earns:
    - **At least 20% more** than the average of their direct subordinates
    - **No more than 50% more**
- Flags employees with **more than 4 levels of managers** above them
- Console-based output with clean separation of logic and presentation
- Built using **Java 8** and **JUnit 5**
- Packaged with **Maven** (supports unit tests and builds)

---

## Project Structure

```
src/
├── main/
│   └── java/com/bigcompany/
│       ├── Main.java
│       ├── model/
│       │   ├── Employee.java
│       │   ├── Violation.java  
│       │   ├── SalaryViolation.java  
│       │   └── DepthViolation.java   
│       ├── rules/
│       │   ├── DepthValidationRule.java
│       │   ├── SalaryValidationRule.java
│       │   └── ValidationRule.java
│       ├── data/
│       │   ├── ConsoleOutputHandler.java
│       │   ├── EmployeeDataSource.java
│       │   ├── CSVEmployeeDataSource.java
│       │   └── OutputHandler.java
│       ├── service/OrgAnalyzer.java
│       └── util/Constants.java
│           
├── test/
│   └── java/com/bigcompany/
│       ├── data/
│       │   ├── MockEmployeeDataSource.java
│       │   ├── MockEmployeeDataSourceDepthViolation.java
│       │   ├── MockEmployeeDataSourceSalaryViolationLow.java
│       │   ├── MockEmployeeDataSourceSalaryViolationHigh.java
│       │   └── MockEmployeeDataSourceNoViolation.java
│       └── rules/
│           ├── DepthValidationRuleTest.java
│           └── SalaryValidationRule.java
│
├── employees.csv (sample input)
└── pom.xml
```

---

## How to Run

### Prerequisites

- Java 8 (OpenJDK 1.8)
- Maven 3+

### Build the project

```bash
mvn clean install
```

### Run the app

```bash
java -cp target/big-company-analysis-1.0-SNAPSHOT.jar com.bigcompany.Main employees.csv
```

Make sure `employees.csv` is in the same directory or provide an absolute path.

---

## Run Unit Tests

```bash
mvn test
```

---

## Optional: Run with Docker

If you want to run the app in a Docker container:

```bash
docker build -t big-company-analysis .
docker run --rm big-company-analysis
```
To accept external CSVs, you can override the CMD at runtime:

```bash 
docker run --rm -v $(pwd)/employees-external.csv:/app/employees.csv big-company-analysis
```

---

## CSV Format

The application expects a CSV file with the following format:

```
Id,firstName,lastName,salary,managerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,50000,124
305,Brett,Hardleaf,34000,300
```

- CEO will have an empty ManagerID
- Salaries must be numeric

---

## Trials
### Input
```csv
Id,firstName,lastName,salary,managerId
101,Emily,Stone,85000,
102,David,Nguyen,67000,101
103,Sophia,Lee,62000,101
201,Michael,Kim,58000,102
202,Daniel,Patel,53000,102
301,Ava,Sharma,48000,201
302,Liam,Mehta,56000,202
303,Noah,Banerjee,45000,302
304,Olivia,Kumar,74000,303
305,Marty,Keefe,46000,304
```
### Output
```
Salary violation: Daniel Patel earns 53000.00 (should be between 67200.00 and 84000.00). Violation type: TOO_LOW (-31000.00)
Salary violation: Noah Banerjee earns 45000.00 (should be between 88800.00 and 111000.00). Violation type: TOO_LOW (-66000.00)
Salary violation: Olivia Kumar earns 74000.00 (should be between 55200.00 and 69000.00). Violation type: TOO_HIGH (5000.00)
Depth violation: Marty Keefe has reporting line of 5, which is more by 1 (limit: 4)
```


## Assumptions

- Always CEO is present in the file
- There's exactly one CEO (employee with no manager)
- Manager-subordinate relationships form a valid tree
- Salaries are positive decimal numbers
- Not considering CEO as Manager for depth violations
- Not considering CEO salary for salary violations

---

## Execution Flow

```text
Main →
    OrgAnalyzer →
        EmployeeDataSource.load() →
        For each ValidationRule →
            evaluate(employees) →
            Output Violations
```

## Design Highlights

- Extensible: New rules can be added without modifying the core.
- Testable: Each rule and data source is independently testable.
- Clean Separation: Follows SOLID principles.