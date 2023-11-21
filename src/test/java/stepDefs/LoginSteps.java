package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.LoginPageObjects;
import utilities.ReadPropertyFile;

import java.io.IOException;


public class LoginSteps{

    LoginPageObjects lpo = new LoginPageObjects();

    private final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    public LoginSteps() throws IOException {
    }

    @Given("the user is on the Login page")
    public void userIsOnLoginPage() {

        String actualLoginPageTitle = lpo.getLoginPageTitle();
        String expectedLoginPageTitle = "Login";

        logger.info("Loaded page: {}", actualLoginPageTitle);
        Assert.assertEquals("The page "+ expectedLoginPageTitle+"  could NOT be loaded",expectedLoginPageTitle, actualLoginPageTitle);

    }

    @When("the user provides valid login credentials: {string} and {string}")
    public void theUserProvidesValidLoginCredentials(String username, String password) {
        lpo.enterUsername(username);
        lpo.enterPassword(password);
        logger.info("User entered Username: {}, and Password: {}", username,password);
    }

    @When("the user submits the login form")
    public void userSubmitsLoginForm() {

        lpo.clickLoginBtn();
        logger.info("User clicked on Login button");
    }

    @Given("an user with Admin rights is logged in")
    public void theAdminUserIsLoggedIn() throws IOException {
        theUserProvidesValidLoginCredentials(ReadPropertyFile.getPropertyValue("AdminUsername"), ReadPropertyFile.getPropertyValue("AdminPassword"));
        userSubmitsLoginForm();
        logger.info("User is logged in");

    }

    @Given("MY STEP")
    public void myStep(){
        logger.info("My step is OK");
    }
}
