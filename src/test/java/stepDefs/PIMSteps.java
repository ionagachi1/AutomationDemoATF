package stepDefs;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.PIMPageObjects;

import java.io.IOException;

public class PIMSteps {

    PIMPageObjects ppo = new PIMPageObjects();

    private static final Logger logger = LoggerFactory.getLogger(PIMSteps.class);

    public PIMSteps() throws IOException {
    }

    @Then("the PIM page should be displayed")
    public void thePIMPageIsOpened() throws InterruptedException {
        String actualPIMPageTitle = ppo.getPIMPageTitle();
        String expectedPIMPageTitle = "PIM";
        Assert.assertEquals(actualPIMPageTitle, expectedPIMPageTitle);

        logger.info("User is on page: {}", actualPIMPageTitle);

    }
}
