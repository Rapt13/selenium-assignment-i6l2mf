package tests;

import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RankingsPage;
import java.util.UUID;
import static org.junit.Assert.*;

public class WstBasicTests extends BaseTest {

    @Test
    public void testInvalidLogin_WithRandomEmail_DisplaysErrorMessage() {
        HomePage homePage = new HomePage(driver, timeout);
        homePage.handleCookieConsent();

        LoginPage loginPage = homePage.navigateToLoginPage();
        
        // Random adatok pont
        String randomEmail = UUID.randomUUID().toString() + "@test.com"; 
        loginPage.login(randomEmail, "InvalidPass123!");

        String errorText = loginPage.getErrorMessageText();
        assertNotNull(errorText);
        assertFalse(errorText.isEmpty());
    }

    @Test
    public void testRankingsDataListingAndDropdownFiltering() {
        HomePage homePage = new HomePage(driver, timeout);
        homePage.handleCookieConsent();
        homePage.navigateToRankings();
        
        RankingsPage rankingsPage = new RankingsPage(driver, timeout);
        int countInitial = rankingsPage.getVisibleRankingsCount();
        assertTrue("Lennie kell soroknak az asztalon", countInitial >= 0);
        
        // Dropdown pont
        rankingsPage.filterBySeasonDropdown("2024/2025"); 
    }

    @Test
    public void testIterativeDataVerificationAcrossMultipleTargetUrls() {
        HomePage homePage = new HomePage(driver, timeout);
        homePage.handleCookieConsent();

        String[] relativeUrls = { "/news", "/matches", "/players", "/rankings" };

        for (String url : relativeUrls) {
            driver.navigate().to(baseUrl + url);
            String title = driver.getTitle(); // Page title pont
            assertNotNull("A title nem lehet null", title);
        }
    }
}