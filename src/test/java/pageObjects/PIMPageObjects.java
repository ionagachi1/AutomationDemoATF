package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WebDriverSingleton;

import java.io.IOException;

public class PIMPageObjects  {
    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(xpath = "//h6[normalize-space()='PIM']")
    private WebElement pagePIMTitle;

    public PIMPageObjects() throws IOException {
        // Initialize WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    public String getPIMPageTitle() {
        return pagePIMTitle.getText();
    }
}
