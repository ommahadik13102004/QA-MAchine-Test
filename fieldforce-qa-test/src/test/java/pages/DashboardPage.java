package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * TODO: verify these locators on the real dashboard after logging in.
 */
public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By punchInButton = By.xpath("//button[contains(text(),'Punch In')]");
    // Toasts are commonly rendered by libraries like react-toastify / Toastr
    private final By toastMessage = By.cssSelector(".Toastify__toast, .toast, .toast-message, [role='alert']");
    private final By addCustomerMenuItem = By.xpath("//*[contains(text(),'Add Customer')]");
    private final By logoutButton = By.xpath("//*[contains(text(),'Logout')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDashboardLoaded() {
        return wait.until(ExpectedConditions.urlContains("dashboard")) != null;
    }

    public void clickPunchIn() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(punchInButton));
        btn.click();
    }

    public String getToastMessageText() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
        return toast.getText();
    }

    public boolean isToastDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToAddCustomer() {
        driver.findElement(addCustomerMenuItem).click();
    }
}
