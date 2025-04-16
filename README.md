# Lightweight API Test Automation Framework

This is a lightweight API test automation framework for validating endpoints of the [Open Charge Map API](https://api.openchargemap.io/v3/). It focuses on automated testing of two key endpoints:
- `poi` – Point of Interest data
- `referencedata` – Static reference data (e.g., connection types, usage types)

## 🧰 Tech Stack

- **Java 21**
- **Maven** for dependency management
- **Rest-Assured** for API interaction
- Uses **TestNG** as the test framework
- **Allure** for test reporting
- - Simple environment configuration via `.properties` files
- **SLF4J + Logback** for logging

---
💻 Working with This Project
1. Clone the repository:
```
git clone https://github.com/<your-username>/openchargemap-api-tests.git
cd openchargemap-api-tests
```
2. Add your API key (as shown in the Configuration section)
3. Run the tests:
```shell
mvn clean test
```
All tests are executed against the live https://api.openchargemap.io/v3/ base URL.
After the tests have been executed, you can generate and view an Allure report using the following steps.
4. Generate the report:
```shell
mvn allure:report
```
5. View Allure report. Open the report in your browser:
```shell
mvn allure:serve
```
This will spin up a local server and open the Allure report. To stop it just press Ctrl+C. and confirm.

## 🔧 Configuration

Before running tests, you must provide a valid API key.

1. Open the file:

src/main/resources/config/prod.properties

2. Add your API key which you can obtain from [here](https://map.openchargemap.io/#/search )
```properties
api.key=your_valid_api_key_here
```
In this file you can also configure the base URL and the maximum response time for the tests.