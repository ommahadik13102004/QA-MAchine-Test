package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AddCustomerPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class AddCustomerSteps {

    private WebDriver driver;
    private AddCustomerPage addCustomerPage;
    private String confirmationText;

    private static final String VALID_EMAIL = "your_valid_email@example.com";
    private static final String VALID_PASSWORD = "your_valid_password";

    @Given("the user is logged in with valid credentials")
    public void the_user_is_logged_in_with_valid_credentials() {
        driver = DriverFactory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(ConfigReader.get("base.url"));
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);
    }

    @When("the user adds a customer named {string} with mobile {string} email {string} and address {string}")
    public void the_user_adds_a_customer(String name, String mobile, String email, String address) {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToAddCustomer();

        addCustomerPage = new AddCustomerPage(driver);
        addCustomerPage.addCustomer(name, mobile, email, address);
        confirmationText = addCustomerPage.getConfirmationText();
    }

    @Then("a success confirmation should be displayed")
    public void a_success_confirmation_should_be_displayed() {
        Assert.assertTrue(confirmationText.toLowerCase().contains("success"));
        DriverFactory.quitDriver();
    }
}
