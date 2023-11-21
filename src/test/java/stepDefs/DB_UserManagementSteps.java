package stepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.dao.UserDAO;
import persistence.entity.User;
import utilities.ScenarioContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DB_UserManagementSteps {

    ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private final Logger logger = LoggerFactory.getLogger(DB_UserManagementSteps.class);

    UserDAO userDAO = new UserDAO();


    @Given("a new user entity is created with the following user details")
    public void aNewUserEntityIsCreatedWithTheFollowingUserDetails(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);
        User user = new User();
        user.setUsername(userData.get("username"));
        user.setPassword(userData.get("password"));
        user.setFirstName(userData.get("firstname"));
        user.setLastName(userData.get("lastname"));
        user.setEmail(userData.get("email"));

        logger.info("New entity is created for user with username: {}, password: {}, firstname: {}, lastname: {}, email: {}",
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());

        scenarioContext.setContextObject("NEW_USER", user);
    }

    @When("the user is inserted in the database")
    public void theUserIsInsertedInTheDatabase() {
        User user = scenarioContext.getContextObject("NEW_USER");
        userDAO.insertUser(user);
        logger.info("New user with username: {} is saved in Users table", user.getUsername());
    }

    @And("the user is queried by its username")
    public void theUserIsQueriedByItsUsername() {
        User expectedUser = scenarioContext.getContextObject("NEW_USER");
        String expectedUsername = expectedUser.getUsername();

        User retrievedUser = userDAO.getUserByUsername(expectedUsername);
        logger.info("Queried for user with username: {}", expectedUsername);
        scenarioContext.setContextObject("RETRIEVED_USER", retrievedUser);

    }

    @Then("the user details should be retrieved from the users table")
    public void theCorrectUserDetailsShouldBeRetrievedFromTheUsersTable() {
        User expectedUser = scenarioContext.getContextObject("NEW_USER");
        User retrievedUser = scenarioContext.getContextObject("RETRIEVED_USER");
        logger.info("Retrieved user details for username: {}", retrievedUser.getUsername());
        assertNotNull("Retrieved user details should not be null", retrievedUser);
        assertEquals("Expected username does not match retrieved username", expectedUser.getUsername(), retrievedUser.getUsername());
        assertEquals("Expected password does not match retrieved password", expectedUser.getPassword(), retrievedUser.getPassword());
        assertEquals("Expected first name does not match retrieved first name", expectedUser.getFirstName(), retrievedUser.getFirstName());
        assertEquals("Expected last name does not match retrieved last name", expectedUser.getLastName(), retrievedUser.getLastName());
        assertEquals("Expected email does not match retrieved email", expectedUser.getEmail(), retrievedUser.getEmail());

    }

    @Given("the following user entities are prepared with specific details")
    public void theFollowingUserEntitiesAreCreatedWithSpecificDetails(DataTable dataTable) {
        List<Map<String, String>> usersData = dataTable.asMaps(String.class, String.class);

        List<User> users = new ArrayList<>();

        for (Map<String, String> userData : usersData) {
            User user = new User();
            user.setUsername(userData.get("username"));
            user.setPassword(userData.get("password"));
            user.setFirstName(userData.get("firstname"));
            user.setLastName(userData.get("lastname"));
            user.setEmail(userData.get("email"));
            users.add(user);
        }

        scenarioContext.setContextObject("BULK_USERS", users);
    }

    @When("the user entities are inserted into the database")
    public void theUsersAreInsertedIntoTheDatabase() {
        List<User> users = scenarioContext.getContextObject("BULK_USERS");
        for (User user : users) {
            userDAO.insertUser(user);
        }
        logger.info("all the users have been inserted into the database");
    }

    @Then("all the users should be successfully added in the users table")
    public void allTheUsersShouldBeSuccessfullyAddedInTheUsersTable() {

    }
}
