package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

/**
 * Task item 2: "Verify the Toast/Popup message after the PunchIn".
 * Update VALID_EMAIL / VALID_PASSWORD with a real account created during signup.
 */
public class PunchInTest extends BaseTest {

    private static final String VALID_EMAIL = "your_valid_email@example.com";
    private static final String VALID_PASSWORD = "your_valid_password";
    // TODO: replace with the exact toast copy shown by the app, e.g. "Punched In Successfully"
    private static final String EXPECTED_TOAST_TEXT = "Punch";

    @Test
    public void testPunchInShowsToast() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickPunchIn();

        Assert.assertTrue(dashboardPage.isToastDisplayed(), "Toast/popup did not appear after Punch In");

        String toastText = dashboardPage.getToastMessageText();
        Assert.assertTrue(toastText.toLowerCase().contains(EXPECTED_TOAST_TEXT.toLowerCase()),
                "Toast text was '" + toastText + "', expected it to contain '" + EXPECTED_TOAST_TEXT + "'");
    }
}
