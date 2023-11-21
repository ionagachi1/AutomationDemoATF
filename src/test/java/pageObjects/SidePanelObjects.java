package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WebDriverSingleton;

import java.io.IOException;

public class SidePanelObjects {

    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(className = "oxd-main-menu")
    private WebElement pageSidePanel;

       public SidePanelObjects() throws IOException {
        PageFactory.initElements(driver, this);
    }

    public boolean getSidePanel() {
           return pageSidePanel.isDisplayed();
    }

    public void clickSidePanelItem(String Item)  {
        String xpathExpression = "//span[normalize-space()='" + Item + "']";
        WebElement sidePanelItem = driver.findElement(By.xpath(xpathExpression));
        sidePanelItem.click();
    }

}


