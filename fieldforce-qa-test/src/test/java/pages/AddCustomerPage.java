package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * TODO: verify these locators against the real "Add Customer" form.
 */
public class AddCustomerPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By customerNameField = By.cssSelector("input[name='customerName'], input[name='name']");
    private final By mobileField = By.cssSelector("input[name='mobile'], input[name='phone']");
    private final By emailField = By.cssSelector("input[name='email']");
    private final By addressField = By.cssSelector("textarea[name='address'], input[name='address']");
    private final By saveButton = By.xpath("//button[contains(text(),'Save') or contains(text(),'Submit') or contains(text(),'Add')]");
    private final By successToast = By.cssSelector(".Toastify__toast, .toast, [role='alert']");

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterCustomerName(String name) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(customerNameField));
        el.clear();
        el.sendKeys(name);
    }

    public void enterMobile(String mobile) {
        driver.findElement(mobileField).sendKeys(mobile);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public void addCustomer(String name, String mobile, String email, String address) {
        enterCustomerName(name);
        enterMobile(mobile);
        enterEmail(email);
        enterAddress(address);
        clickSave();
    }

    public String getConfirmationText() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
        return toast.getText();
    }
}
