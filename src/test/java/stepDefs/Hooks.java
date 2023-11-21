package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.HibernateFrameworkSetup;
import utilities.ScenarioContext;
import utilities.WebDriverSingleton;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Scenario execution started");
    }

    @After
    public void tearDown() {
        WebDriverSingleton.closeInstance();
        ScenarioContext.clearContext();
        HibernateFrameworkSetup.closeSessionFactory();
        System.out.println("Scenario execution finished");
    }
}
