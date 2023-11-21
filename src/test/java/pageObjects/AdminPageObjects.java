package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WebDriverSingleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminPageObjects {

    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(xpath = "//h6[normalize-space()='Admin']")
    private WebElement pageAdminTitle;

    @FindBy(className = "oxd-topbar-body")
    private WebElement menuContainer;
    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(className = "orangehrm-card-container")
    private WebElement addForm;

    @FindBy(xpath = "(//div[@class='oxd-select-text--after'])[1]")
    private WebElement roleArrowDown;

    @FindBy(xpath = "(//div[@class='oxd-select-text--after'])[2]")
    private WebElement statusArrowDown;
    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    private WebElement employeeNameField;
    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    private WebElement employeeUsernameField;
    @FindBy(xpath = "(//input[@type='password'])[1]")
    private WebElement employeePasswordField;
    @FindBy(xpath = "(//input[@type='password'])[2]")
    private WebElement employeeConfirmPasswordField;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
    private WebElement usernameSearchField;
    @FindBy(xpath = "(//button[normalize-space()='Search'])[1]")
    private WebElement searchButton;
    @FindBy(xpath = "(//div[@role='cell'])[2]")
    private WebElement tableEmployeeUsername;


    public AdminPageObjects() throws IOException {
        // Initialize WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    public String getAdminPageTitle() {
        return pageAdminTitle.getText();
    }

    public List<String> getMenuItemsFromAdminPage(){
        List<WebElement> menuItemsElements = menuContainer.findElements(By.className("oxd-topbar-body-nav-tab-item"));
        List<String> menuItems = new ArrayList<>();

        for (WebElement menuItem : menuItemsElements) {
            menuItems.add(menuItem.getText());
        }
        return menuItems;
    }

    public void clickAddBtn(){
        addButton.click();
    }
    public boolean getAddForm() {
        return addForm.isDisplayed();
    }

    public void clickRoleArrowDown(){
        roleArrowDown.click();
    }

    public void selectRole(String roleItem) {
        String roleItemXpath = "//span[contains(text(),'"+roleItem+"')]";
        WebElement userRoleItem = driver.findElement(By.xpath(roleItemXpath));
        userRoleItem.click();
    }

    public void clickStatusArrowDown(){
        statusArrowDown.click();
    }
    public void selectStatus(String statusItem) {
        String statusItemXpath = "//span[normalize-space()='"+statusItem+"']";
        WebElement userStatusItem = driver.findElement(By.xpath(statusItemXpath));
        userStatusItem.click();
    }

    public void enterEmployeeName(String employeeName) {
        employeeNameField.sendKeys(employeeName);
        String employeeNameXpath = "//span[contains(text(),'"+employeeName+"')]";
        WebElement employeeNameItem = driver.findElement(By.xpath(employeeNameXpath));
        employeeNameItem.click();
    }
    public void enterEmployeeUsername(String employeeUsername) {
       employeeUsernameField.sendKeys(employeeUsername);
    }
    public void enterEmployeePassword(String employeePassword) {
        employeePasswordField.sendKeys(employeePassword);
    }
    public void confirmPassword(String employeeConfirmPassword) {
        employeeConfirmPasswordField.sendKeys(employeeConfirmPassword);
    }
    public void clickSaveBtn(){
        saveButton.click();
    }
    public void enterSearchUsername (String searchUsername)
    {
        usernameSearchField.sendKeys(searchUsername);
    }

    public void clickSearchBtn(){
        searchButton.click();
    }

    public String getEmployeeUsernameFromEmployeesTable()  {
        return tableEmployeeUsername.getText();

    }
}
