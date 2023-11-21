package stepDefs;
import actions.ValidateAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ReadPropertyFile;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import utilities.ScenarioContext;
import java.io.IOException;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class API_Steps {
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    private final Logger logger = LoggerFactory.getLogger(API_Steps.class);
    ValidateAPI vAPI = new ValidateAPI();

    protected RequestSpecification getRequest() {
        RequestSpecification request = scenarioContext.getContextObject("API_REQUEST");
        if (request == null) {
            request = RestAssured.given();
            scenarioContext.setContextObject("API_REQUEST", request);
        }
        return request;
    }

    @Given("I set the base URI")
    public void iSetTheBaseURI() throws IOException {
        String baseUri = ReadPropertyFile.getPropertyValue("baseURI");
        RestAssured.baseURI = baseUri;
        scenarioContext.setContextObject("BASE_URI", baseUri);
        logger.info("Base URI set to: {}", baseUri);

        System.out.println(baseUri);
    }

    @And("I set the request body with JSON")
    public void iSetTheRequestBodyWithJSON(String requestBody) {
        getRequest().contentType(ContentType.JSON).body(requestBody);
        //getRequest().body(requestBody);

        logger.info("Request body is set to: {}",requestBody);
    }

    @When("I send a POST request to {string}")
    public void iSendAPostRequestTo(String loginEndpoint) {

        Response response = getRequest().when().post(loginEndpoint);
        scenarioContext.setContextObject("API_LOGIN_RESPONSE", response);
        logger.info("Sent POST request to: {}", loginEndpoint);
    }

    @Then("The response {string} should be valid")
    public void theResponseShouldBeValid(String apiResponseName) {
        Response response = scenarioContext.getContextObject(apiResponseName);
        assertNotNull("Response is NULL", response);
        logger.info("Response is not NULL");
        assertFalse("Response is empty", response.getBody().asString().isEmpty());
        logger.info("Response is not empty");
    }

    @And("I should receive a status code of {int} for {string}")
    public void iShouldReceiveAStatusCodeOfFor(int statusCode, String apiResponseName) {
        Response response = scenarioContext.getContextObject(apiResponseName);
        int actualStatusCode = response.getStatusCode();
        assertThat(actualStatusCode).isEqualTo(statusCode);
        logger.info("Expected status code: {}, Actual status code: {}", statusCode, actualStatusCode);
    }


    @And("I receive valid token")
    public void iReceiveValidToken() {
        Response response = scenarioContext.getContextObject("API_LOGIN_RESPONSE");
        String actualBody = response.getBody().asString();
        JsonObject actualBodyJson = JsonParser.parseString(actualBody).getAsJsonObject();

        String token = vAPI.getToken(actualBodyJson);
        scenarioContext.setContextObject("AUTH_TOKEN", token);

        Assert.assertNotNull("Token value is NULL.......",token);
        logger.info("Token value is: {}",token);
    }


    @And("I send a POST Login request to {string} with JSON")
    public void iSendAPOSTLoginRequestToWithJSON(String loginPath, String requestBody)  {
        iSetTheRequestBodyWithJSON(requestBody);
        iSendAPostRequestTo(loginPath);
    }

    @When("I send GET request to {string} with token")
    public void iSendGETRequestTo(String contactsEndpoint)  {

        getRequest().header("Authorization", "Bearer " + scenarioContext.getContextObject("AUTH_TOKEN"));
        Response response = getRequest().when().get(contactsEndpoint);

        scenarioContext.setContextObject("API_CONTACTS_RESPONSE", response);

        logger.info("Sent GET request to: {}", contactsEndpoint);
        logger.info("Response: {}", response.getBody().asString());


        System.out.println(response.getBody().asString());

    }

    @And("I should validate the LOGIN API response body")
    public void iShouldValidateTheUserResponseBody(DataTable dataTable) {

        Response response = scenarioContext.getContextObject("API_LOGIN_RESPONSE");

        String actualBody = response.getBody().asString();
        JsonObject actualBodyJson = JsonParser.parseString(actualBody).getAsJsonObject();
        JsonObject userJsonObject = actualBodyJson.getAsJsonObject("user");

        Map<String, String> expectedLoginDetails = dataTable.asMap(String.class, String.class);

        boolean contentMatches = vAPI.compareJsonObject(expectedLoginDetails,userJsonObject);
        Assert.assertTrue("Body content did not match.........",contentMatches);
        logger.info("All Body Content matched");
    }

    @And("I should validate the CONTACTS API response body")
    public void iShouldValidateTheCONTACTSAPIResponseBody(DataTable dataTable) {
        Response response = scenarioContext.getContextObject("API_CONTACTS_RESPONSE");
        String actualBody = response.getBody().asString();

        JsonArray jsonArray = JsonParser.parseString(actualBody).getAsJsonArray();

        // Split the JSON array and DATATABLE into individual JSONs/MAPa for each user
        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject clientJson = jsonArray.get(i).getAsJsonObject();
            Map<String, String> expectedClientDetails = dataTable.asMaps().get(i);

            boolean contentMatches = vAPI.compareJsonObject(expectedClientDetails,clientJson);
            Assert.assertTrue("Body content did not match for Client: "+(i+1),contentMatches);
            logger.info("Body Content match for Client: "+(i+1));
        }
    }
}
