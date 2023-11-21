package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WebDriverSingleton;

import java.io.IOException;

public class LoginPageObjects {
    WebDriver driver = WebDriverSingleton.getInstance();;

    @FindBy(xpath = "//h5[normalize-space()='Login']")
    private WebElement pageLoginTitle;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;

    public LoginPageObjects() throws IOException {
        // Initialize WebElements using PageFactory
        PageFactory.initElements(driver, this);

    }
    public String getLoginPageTitle() {
        return pageLoginTitle.getText();
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginButton.click();
    }

}


