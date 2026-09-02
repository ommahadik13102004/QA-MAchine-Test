package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * IMPORTANT: The locators below are placeholders (By.id / By.cssSelector
 * guesses based on common naming conventions). Before running the suite,
 * sign up on https://test.fieldforceconnect.com/, open DevTools on the
 * real Login/Signup/PunchIn/Add-Customer screens, and replace each
 * locator with the actual attribute (id, name, data-testid, etc.) you
 * find. Centralizing them here means you only edit this one file per
 * page instead of hunting through every test.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // TODO: confirm/replace these against the real DOM
    private final By emailField = By.cssSelector("input[type='email'], input[name='email']");
    private final By passwordField = By.cssSelector("input[type='password'], input[name='password']");
    private final By loginButton = By.xpath("//button[contains(text(),'Login') or contains(text(),'Sign In')]");
    private final By errorMessage = By.cssSelector(".error-message, .toast-error, [class*='error']");
    private final By forgotPasswordLink = By.linkText("Forgot Password?");
    private final By signInWithOtpLink = By.xpath("//*[contains(text(),'OTP')]");
    private final By signUpLink = By.linkText("Sign Up");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    public void enterEmail(String email) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        el.clear();
        el.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement el = driver.findElement(passwordField);
        el.clear();
        el.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorText() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return driver.findElement(forgotPasswordLink).isDisplayed();
    }

    public boolean isSignInWithOtpLinkDisplayed() {
        return driver.findElement(signInWithOtpLink).isDisplayed();
    }
}
