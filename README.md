# FieldForceConnect — QA Machine Test (Nimap Infotech)

Candidate: Om Mahadik
Target application: https://test.fieldforceconnect.com/
Deadline: 04-Sept-26, 2 PM

This repo covers all three parts of the machine test:

```
fieldforce-qa-test/
├── pom.xml                          # Maven project (Selenium + TestNG + Cucumber)
├── testng.xml                       # TestNG suite runner
├── src/test/java/
│   ├── pages/                       # Page Object classes (Login, Dashboard, AddCustomer)
│   ├── tests/                       # TestNG tests: LoginTest, PunchInTest, AddCustomerTest
│   ├── stepdefinitions/             # Cucumber step definitions (BDD, optional layer)
│   ├── runners/CucumberTestRunner.java
│   └── utils/                       # DriverFactory, ConfigReader, CsvDataReader
├── src/test/resources/
│   ├── config.properties            # base URL / browser config
│   ├── testdata/                    # login_data.csv, customer_data.csv (parametrization)
│   └── features/                    # Cucumber .feature files
├── manual-testing/
│   └── TestCases.md                 # Manual test cases + field validations + bug log
└── postman/
    ├── FieldForceConnect.postman_collection.json
    └── FieldForceConnect.postman_environment.json
```

## How to run the automation (TestNG)

```bash
mvn clean test
```

This runs `testng.xml`, which executes `LoginTest`, `PunchInTest`, and
`AddCustomerTest` in sequence. Chrome is launched automatically via
WebDriverManager (no manual driver download needed).

## How to run the Cucumber (BDD) layer

```bash
mvn test -Dtest=CucumberTestRunner
```

Feature files live in `src/test/resources/features/`; step definitions are
in `src/test/java/stepdefinitions/`. HTML report is generated at
`target/cucumber-report.html`.

