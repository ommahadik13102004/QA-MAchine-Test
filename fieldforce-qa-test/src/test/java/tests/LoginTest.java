package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvDataReader;

/**
 * Task item 1: "Automate Login Journey (by parametrization technique and Validate it)".
 * Credentials are pulled from src/test/resources/testdata/login_data.csv so
 * the same test runs once per row (valid creds -> dashboard, invalid -> error toast).
 */
public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return CsvDataReader.readCsv("src/test/resources/testdata/login_data.csv");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);
        loginPage.login(email, password);

        if ("valid".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),
                    "Expected to land on dashboard after valid login, but URL was: " + driver.getCurrentUrl());
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Expected an error message for invalid credentials but none was shown");
        }
    }
}
