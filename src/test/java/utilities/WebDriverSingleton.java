package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

public class WebDriverSingleton {
    private static WebDriver driver;

    private static final String BROWSER;
    private static final boolean BROWSER_HEADLESS;

    static {
        try {
            BROWSER = ReadPropertyFile.getPropertyValue("browser");
            BROWSER_HEADLESS = Boolean.parseBoolean(ReadPropertyFile.getPropertyValue("browser_headless"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(WebDriverSingleton.class);


    private WebDriverSingleton() {
        // Private constructor to prevent direct instantiation
    }

    public static WebDriver getInstance() throws IOException {
        if (driver == null) {
            // Initialize the WebDriver based on the chosen browser
            switch (BROWSER.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if(BROWSER_HEADLESS){
                        chromeOptions.addArguments("--headless");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions fireFoxOptions = new FirefoxOptions();
                    if(BROWSER_HEADLESS) {
                        fireFoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(fireFoxOptions);
                    break;
                default:
                    logger.error("Unsupported browser: {} ", BROWSER);
                    throw new IllegalStateException("Unsupported browser: " + BROWSER);
            }
            driver.get(ReadPropertyFile.getPropertyValue("testurlLogin"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            logger.info("WebDriver was set up successfully");
        }

        return driver;
    }

    public static void closeInstance() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        logger.info("WebDriver was closed successfully");
    }
}