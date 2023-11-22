package stepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.AdminPageObjects;
import pageObjects.Navigate;
import utilities.GenerateRandom;
import utilities.ScenarioContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class AdminSteps {

    AdminPageObjects apo = new AdminPageObjects();
    Navigate navigate = new Navigate();

    private final Logger logger = LoggerFactory.getLogger(AdminSteps.class);

    ScenarioContext scenarioContext = ScenarioContext.getInstance();


    public AdminSteps() throws IOException {
    }

    @When("the Admin page is displayed")
    @Then("the Admin page should be displayed")
    public void theAdminPageIsOpened() {
        String actualAdminPageTitle = apo.getAdminPageTitle();
        String expectedAdminPageTitle = "Admin";

        logger.info("Loaded page: {}", actualAdminPageTitle);
        Assert.assertEquals("The page "+ expectedAdminPageTitle+"  could NOT be loaded", expectedAdminPageTitle, actualAdminPageTitle);
        System.out.println("The 1st push on Github");
        System.out.println("The 2nd push on Github");
    }

    @Given("the user navigates to the Admin page using URL")
    public void theUserNavigatesToAdminPage() throws IOException {
        navigate.toAdminPage();
    }



    @Then("the Top bar Menu should include the following listed items")
    public void theTopBarMenuItemsShouldIncludeItems(DataTable dataTable) {
        List<String> expectedMenuItems = dataTable.asList(); // to be changed in a simple list
        List<String> actualMenuItems = apo.getMenuItemsFromAdminPage();
        boolean areMenuItemsEqual = CollectionUtils.isEqualCollection(expectedMenuItems, actualMenuItems);

        logger.info("The Top Bar Menu has the following options {}", actualMenuItems);
        logger.info("The Expected Top Bar Menu options should be {}", expectedMenuItems);
        Assert.assertTrue("The actual menu items are different than expected menu items",areMenuItemsEqual);
    }

    @When("the user initiates the employee creation")
    public void userInitiatesEmployeeCreation() {
        apo.clickAddBtn();
        logger.info("User initiated the Employee creation");
    }

    @When("user submits the Add User form with the following valid employee details")
    public void userSubmitsTheAddUserFormWithTheFollowingValidEmployeeDetails(DataTable dataTable) {
        boolean isAddFormPresent = apo.getAddForm();
        logger.info("The Add Form is present");
        Assert.assertTrue("The Add Form is missing",isAddFormPresent);

        Map<String, String> employeeData = dataTable.asMap(String.class, String.class);

        String role = employeeData.get("Role");
        String status = employeeData.get("Status");
        String employeeName = employeeData.get("EmployeeName");
        String employeeUsername = employeeData.get("EmployeeUsername");
        String password = employeeData.get("Password");
        String confirmPassword = employeeData.get("ConfirmPassword");

        apo.clickRoleArrowDown();
        logger.info("User clicked on Arrow down from User Role list box");

        apo.selectRole(role);
        logger.info("User selected {} option", role);

        apo.clickStatusArrowDown();
        logger.info("User clicked on Arrow down from Status list box");

        apo.selectStatus(status);
        logger.info("User selected {} option", status);

        apo.enterEmployeeName(employeeName);
        logger.info("User selected employee name {}", employeeName);

        GenerateRandom gr = new GenerateRandom();
        String finalEmployeeUsername = gr.addRandomSuffix(employeeUsername);
        apo.enterEmployeeUsername(finalEmployeeUsername);
        logger.info("User entered username {}", finalEmployeeUsername);

        scenarioContext.setContextObject("EMPLOYEE_USERNAME",finalEmployeeUsername);


        apo.enterEmployeePassword(password);
        logger.info("User entered password {}", password);

        apo.confirmPassword(confirmPassword);
        logger.info("User confirmed password {}", confirmPassword);

        apo.clickSaveBtn();
        logger.info("User clicked on Save button");

    }

    @And("user searches for the newly created employee")
    public void searchForCreatedEmployee() {
        String sc_EMPLOYEE_NAME = scenarioContext.getContextObject("EMPLOYEE_USERNAME");

        apo.enterSearchUsername(sc_EMPLOYEE_NAME);
        logger.info("User entered {} in Search username field", sc_EMPLOYEE_NAME);

        apo.clickSearchBtn();
        logger.info("User clicked on Search button");
    }

    @Then("the employee should be found in the Employees table")
    public void theEmployeeIsFoundInEmployeesTable() {

        String expectedEmployeeUsername = scenarioContext.getContextObject("EMPLOYEE_USERNAME");

        await().atMost(10, TimeUnit.SECONDS).until(() -> {
            String tableEmployeeUsername = apo.getEmployeeUsernameFromEmployeesTable();
            return tableEmployeeUsername.equals(expectedEmployeeUsername);
        });
        String tableEmployeeUsername = apo.getEmployeeUsernameFromEmployeesTable();

        logger.info("User {} is present in Employees table", tableEmployeeUsername);
        Assert.assertEquals("Employee is not present in Employees table",expectedEmployeeUsername, tableEmployeeUsername);

    }


}
