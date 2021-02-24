package com.db.camunda.e2e.steps;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;

public class E2eStepdefs {

    private static final String BASE_URL = "http://localhost:8080";
    private Response response;

    public E2eStepdefs() {
        RestAssured.baseURI = BASE_URL;
    }

    @Given("rules are ready in excel file {string}")
    public void rulesAreReadyInExcelFileTestExcelFilesTestRulesXlsx(String excel) {
        Assertions.assertTrue(Files.exists(Paths.get(excel)));
    }

    @When("I upload the excel file by calling api {string}")
    public void iUploadTheExcelFileByCallingApiAdminRulesImport(String path) {
        response =
                given().multiPart("file", new File("testExcelFiles/testRules.xlsx"))
                        .post(path)
                        .then()
                        .extract()
                        .response();
    }

    @Then("I should get {int} status")
    public void iShouldGetStatus(int code) {
        Assertions.assertEquals(code, response.getStatusCode());
    }

    @And("the response message should contain {string}")
    public void theResponseMessageShouldContain(String message) {
        String responseMessage = response.jsonPath().get("message");
        Assertions.assertEquals(message, responseMessage);
    }

    @Given("I have a month")
    public void iHaveAMonth() {}

    @When("I check with api {string}")
    public void iCheckWithApi(String path) {
        response = given().get(path).thenReturn();
    }
}
