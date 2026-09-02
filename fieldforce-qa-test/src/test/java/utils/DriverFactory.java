package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Creates and tears down the WebDriver instance used by every test.
 * Uses a ThreadLocal so the suite can later be run in parallel safely.
 */
public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initDriver();
        }
        return driver.get();
    }

    private static void initDriver() {
        String browser = ConfigReader.get("browser");

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            driver.set(new ChromeDriver(options));
        } else {
            throw new RuntimeException("Browser not supported yet: " + browser);
        }

        int implicitWait = Integer.parseInt(ConfigReader.get("implicit.wait.seconds"));
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
