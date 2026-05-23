package tests;

import org.junit.Test;
import org.openqa.selenium.Cookie;
import pages.HomePage;
import static org.junit.Assert.*;

public class WstAdvancedTests extends BaseTest {

    @Test
    public void testBrowserHistoryNavigation() {
        HomePage homePage = new HomePage(driver, timeout);
        homePage.handleCookieConsent();
        
        driver.navigate().to(baseUrl + "/news");
        assertTrue(driver.getTitle().toLowerCase().contains("news") || driver.getCurrentUrl().contains("news"));
        
        // History test pont (back & forward)
        driver.navigate().back();
        assertTrue(driver.getCurrentUrl().equals(baseUrl) || driver.getCurrentUrl().equals(baseUrl + "/"));
        
        driver.navigate().forward();
        assertTrue(driver.getCurrentUrl().contains("news"));
    }

    @Test
    public void testCookieManipulation_AddsAndVerifiesCustomCookie() {
        HomePage homePage = new HomePage(driver, timeout);
        
        // Cookie manipuláció pont
        Cookie testCookie = new Cookie("test_automation", "active");
        driver.manage().addCookie(testCookie);
        
        Cookie retrievedCookie = driver.manage().getCookieNamed("test_automation");
        assertNotNull("A cookie-t sikeresen be kellett volna állítani", retrievedCookie);
        assertEquals("active", retrievedCookie.getValue());
        
        driver.manage().deleteCookieNamed("test_automation");
        assertNull(driver.manage().getCookieNamed("test_automation"));
    }
}