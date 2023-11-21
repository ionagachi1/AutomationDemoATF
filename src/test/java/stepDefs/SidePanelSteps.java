package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.SidePanelObjects;

import java.io.IOException;


public class SidePanelSteps {

    SidePanelObjects spo = new SidePanelObjects();

    private static final Logger logger = LoggerFactory.getLogger(SidePanelSteps.class);

    public SidePanelSteps() throws IOException {
    }

    @And("the SidePanel is displayed")
    @Then("the SidePanel should be displayed")
    public void theSidePanelIsDisplayed() {
        boolean isSidePanelPresent = spo.getSidePanel();

        logger.info("The Side Panel is present");
        Assert.assertTrue("The Side Panel is not present",isSidePanelPresent);

    }


    @When("the user navigates to the Admin page using side bar link")
    public void theUserClicksOnItem() {
        spo.clickSidePanelItem("Admin");
        logger.info("User clicked on Admin item in Side Panel");
    }

    @When("the user navigates to the PIM page using side bar link")
    public void theUserClicksOnPIMItem() {
        spo.clickSidePanelItem("PIM");
        logger.info("User clicked on PIN item in Side Panel");
    }

}
