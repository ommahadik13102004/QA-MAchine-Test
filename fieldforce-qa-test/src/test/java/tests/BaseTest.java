package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        baseUrl = ConfigReader.get("base.url");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
