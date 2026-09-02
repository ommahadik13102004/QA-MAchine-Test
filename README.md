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

## ⚠️ Before you run anything — do this first

The automation code below is a complete, working **framework** (Page Object
Model + TestNG DataProviders + Cucumber), but every locator (`By.id`,
`By.cssSelector`, etc.) is a **placeholder** — I don't have a login to the
actual app, so I can't inspect its real DOM. You must:

1. Sign up on https://test.fieldforceconnect.com/ to get a real account.
2. Open DevTools (F12) → Inspect the Login form, Punch-In button/toast, and
   Add Customer form.
3. Update the locators in `src/test/java/pages/LoginPage.java`,
   `DashboardPage.java`, and `AddCustomerPage.java` to match the real
   `id` / `name` / `data-testid` attributes.
4. Put your real credentials into `login_data.csv` (row marked "valid"),
   and into the `VALID_EMAIL` / `VALID_PASSWORD` constants in
   `PunchInTest.java` and `AddCustomerTest.java`.
5. For Postman, open the Network tab while logging in and adding a customer,
   copy the real request URL + JSON body, and replace the `TODO` placeholders
   in `FieldForceConnect.postman_collection.json`.

This is normal for a take-home QA test — the brief expects you to explore
the app yourself. The structure, waits, data-driven design, and assertions
are all in place so you're only swapping in real selectors/endpoints, not
writing the framework from scratch.

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

## Manual testing

Open `manual-testing/TestCases.md` — it has test cases and field validations
for Sign Up, Forgot Password, Sign in with OTP, and Login, plus a bug log
table at the bottom to fill in as you find issues.

## Postman / API testing

1. Import both files from `postman/` into Postman (environment + collection).
2. Select the "FieldForceConnect - Test" environment.
3. Update the `TODO` endpoint paths/bodies per the note above.
4. Run "Login - Valid Credentials" first — its test script auto-saves the
   returned token into `{{auth_token}}` for use by "Add Customer".
5. Run "Login - Invalid Credentials" to confirm the negative case.
6. Run "Add Customer" (and the sample GET request) using the saved token.

## Notes on the task brief

- Login automation uses CSV-based parametrization (`login_data.csv`) — one
  test method runs once per row, covering both valid and invalid cases.
- `PunchInTest` asserts the toast/popup that appears after Punch In is
  displayed and contains the expected text (update `EXPECTED_TOAST_TEXT`
  once you see the real copy).
- Add Customer automation is parametrized via `customer_data.csv` and
  validates a success confirmation toast after submission.
