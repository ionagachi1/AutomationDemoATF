package pageObjects;


import org.openqa.selenium.WebDriver;
import stepDefs.AdminSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ReadPropertyFile;
import utilities.WebDriverSingleton;

import java.io.IOException;

public class Navigate {
    WebDriver driver = WebDriverSingleton.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(Navigate.class);

    public Navigate() throws IOException {
    }

    public void toAdminPage() throws IOException {
        logger.info("Admin URL: " + ReadPropertyFile.getPropertyValue("testurlAdmin"));
        driver.navigate().to(ReadPropertyFile.getPropertyValue("testurlAdmin"));
    }
    public void toPIMPage() throws IOException {
        logger.info("PIM URL: " + ReadPropertyFile.getPropertyValue("testurlPIM"));
        driver.navigate().to(ReadPropertyFile.getPropertyValue("testurlPIM"));

    }
}
