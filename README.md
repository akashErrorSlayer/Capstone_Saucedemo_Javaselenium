# SauceDemo Selenium TestNG Framework
#As the repository shows HTML as the main language because there are many reports in the project which is in HTML so that's why it's considering as a main laguage but does not affect anything

Ready-to-run Selenium + TestNG framework with **ExtentReports** and **screenshots** for each test.

## Features
- Page Object Model for https://www.saucedemo.com/
- ExtentReports HTML report at `test-output/ExtentReport.html`
- Screenshots on test **failure** (and optional on success) under `test-output/screenshots/`
- WebDriverManager auto-sets up ChromeDriver
- Headless mode support via `-Dheadless=true`

## Requirements
- JDK 11 or higher
- Maven 3.6+
- Chrome installed

## How to run in Eclipse
1. File → Import → Maven → Existing Maven Project → select this folder.
2. Right-click the project → Maven → Update Project.
3. Run with `mvn clean test` or from Eclipse as **TestNG Suite** on `testng.xml`.

## CLI
```bash
mvn clean test
# headless
mvn clean test -Dheadless=true
```

## Default test credentials
- username: `standard_user`
- password: `secret_sauce`
