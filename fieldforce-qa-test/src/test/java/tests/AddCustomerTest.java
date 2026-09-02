package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.CsvDataReader;

/**
 * Task item 3: "Add Customer (using parametrization and Validate it)".
 * Customer records come from src/test/resources/testdata/customer_data.csv.
 */
public class AddCustomerTest extends BaseTest {

    private static final String VALID_EMAIL = "your_valid_email@example.com";
    private static final String VALID_PASSWORD = "your_valid_password";

    @DataProvider(name = "customerData")
    public Object[][] customerData() {
        return CsvDataReader.readCsv("src/test/resources/testdata/customer_data.csv");
    }

    @Test(dataProvider = "customerData")
    public void testAddCustomer(String name, String mobile, String email, String address) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToAddCustomer();

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.addCustomer(name, mobile, email, address);

        String confirmation = addCustomerPage.getConfirmationText();
        Assert.assertTrue(confirmation.toLowerCase().contains("success"),
                "Expected a success confirmation after adding customer '" + name + "', got: " + confirmation);
    }
}
