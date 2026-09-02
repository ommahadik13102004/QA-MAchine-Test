package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("the user is on the FieldForceConnect login page")
    public void the_user_is_on_the_login_page() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.open(ConfigReader.get("base.url"));
    }

    @When("the user enters email {string} and password {string}")
    public void the_user_enters_email_and_password(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @And("clicks the login button")
    public void clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("the login result should be {string}")
    public void the_login_result_should_be(String expectedResult) {
        if ("valid".equalsIgnoreCase(expectedResult)) {
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed());
        }
        DriverFactory.quitDriver();
    }
}
