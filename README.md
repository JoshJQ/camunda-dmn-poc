# camunda-dmn-poc
This is a proof of concept project as a preparation for the coming project. It includes the following features:
- Convert Camunda decision rules from excel file by REST api call
- Save the converted DMN template as a clob field in the database
- Load the decision table in Camunda decision engine and provide decision output via REST api
- Automated test framework to support 3 layers testing, Unit Test/Integration Test/End-To-End Test

Tech stack:
- Gradle
    - nebula release plugin to manage the semantic version
    - spotless plugin to format the codes
- Spring Boot 2
- Spring JPA
- Lombok
- Cucumber
- Rest Assured