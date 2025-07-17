
# Inkarto Hackathon - Automated Testing Framework

This repository contains a comprehensive automated testing framework developed for validating the functionalities of the Inkarto web application. The project is built using industry-standard practices and modern testing tools to ensure high reliability, maintainability, and extensibility.

## Project Objectives

- Conduct end-to-end automated UI testing of the Inkarto website.
- Facilitate cross-browser and parallel testing using Selenium Grid.
- Implement data-driven testing for extensive input coverage.
- Generate structured logs and reports for better traceability.
- Capture failure evidence using automated screenshots.
- Provide a reusable and scalable testing architecture.

## Technologies and Tools Used

Technologies and Tools Used
Programming Language: Java 21

Testing Framework: TestNG, Cucumber (for BDD)

Automation Tool: Selenium WebDriver

Design Pattern: Page Object Model (with PageFactory)

Build Tool: Maven

Logging: Log4j2

Reporting: Allure Reports

Retry Mechanism: TestNG RetryAnalyzer

Parallel Execution: Selenium Grid

Test Data Management:

Excel (using Apache POI)

JSON

.properties files

## Project Structure

```
Inkarto/
└── src/
|    ├── main/
|    │   ├── java/
|    │   │   └── (empty or utility classes if needed)
|    │   └── resources/
|    │       ├── allure.properties
|    │       ├── config.properties
|    │       └── log4j2.xml
|    └── test/
|        ├── java/
|        │   └── com.inkarto/
|        │       ├── hooks/                  # Cucumber hooks (@Before, @After)
|        │       ├── pages/                 # Page Object Model classes
|        │       ├── reRun/                 # Retry logic or failed test reruns
|        │       ├── stepDefinitions/       # Step definitions for feature files
|        │       ├── testRunner/            # TestNG or Cucumber runner classes
|        │       └── utilities/             # Reusable utilities (WebDriver, readers)
|        └── resources/
|            ├── features/                  # Gherkin feature files
|            └── output/                    # Screenshots, logs, or custom artifacts
├── pom.xml                                 # Maven build configuration
├── testng.xml                              # TestNG suite configuration
├── README.md                               # Project overview and setup instructions
├── config.properties                       # Environment and test configuration
├── allure.properties                       # Allure report configuration
├── log4j2.xml                              # Logging configuration
├── target/                                 # Build and report output
│   ├── allure-report/                      # Final Allure HTML report
│   ├── allure-results/                     # Raw Allure test results (JSON, attachments)
│   ├── cucumber-report.html                # Cucumber HTML report
│   ├── report.json                         # Consolidated test result file
│   └── test-output/                        # Default TestNG output folder
```

## Getting Started

### Prerequisites

- Java JDK 21 or higher
- Apache Maven 3.6 or higher
- Chrome and/or Edge browsers
- Allure Command Line Tool (for reports)
- Selenium Grid (for remote execution)

### Build and Run the Project

To clean and build the project:
```
mvn clean install
```

To execute tests using the default suite:
```
mvn test
```

To run a specific suite:
```
mvn test -DsuiteXmlFile=testng.xml
```

To generate and open Allure report:
```
allure serve target/allure-results
```

## Framework Features

### Page Object Model (POM)

The framework uses the Page Object Model with PageFactory to ensure separation of concerns and improve reusability and readability.

### Cross-Browser Testing

Browser support is parameterized via TestNG. The framework supports execution on both Chrome and Edge.

### Cucumber Integration

Supports BDD-style test development using Gherkin feature files and step definitions.

### Data-Driven Testing

Supports dynamic input through external data sources:
- Excel files using Apache POI
- JSON files for structured data
- `.properties` files for configuration

### Retry Mechanism

Includes a custom implementation of TestNG's `IRetryAnalyzer` to rerun failed tests and handle flaky test cases.

### Logging

Logs test execution using Log4j2 with timestamps, log levels, and error details. Logs are saved under `logs/automation.log`.

### Screenshot Capture

Automatically captures a screenshot on test failure. Files are stored under the `screenshots/` directory.

### Selenium Grid Support

Can run tests in parallel across nodes using Selenium Grid for distributed execution.

### Allure Reporting

Provides advanced test reporting using Allure, including detailed step execution and failure analysis.

## Output Locations

- Logs: `logs/automation.log`
- Screenshots: `screenshots/`
- Reports: `target/allure-results/`

## Contributing

This project was developed as part of a hackathon for educational and demonstration purposes. Contributions in the form of improvements, additional test cases, and architectural enhancements are welcome.

## License

This code is intended for non-commercial, educational use only. It is not licensed for production deployments or commercial distribution.

## Contact

For queries or collaboration, please reach out via your team’s communication channels or through the project maintainers.

## Git-Hub URL

https://github.com/Shaikmohammadfaraz123/Bug-Busters_Inkarto.git
