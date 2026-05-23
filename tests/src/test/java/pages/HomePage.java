package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By cookieAcceptButton = By.xpath("//button[contains(@class, 'cookie-banner__accept') or contains(text(), 'Accept All')]");
    private final By rankingsLink = By.xpath("//a[contains(@href, '/rankings')]");
    private final By newsLink = By.xpath("//div[@id='main-nav']//a[contains(@href, '/news')]"); // Komplex XPath
    
    public HomePage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    public void handleCookieConsent() {
        try {
            if (!driver.findElements(cookieAcceptButton).isEmpty()) {
                click(cookieAcceptButton);
            }
        } catch (Exception e) {
            System.out.println("No cookie banner.");
        }
    }

    public LoginPage navigateToLoginPage() {
        driver.get("https://account.wst.tv/login");
        return new LoginPage(driver, 10);
    }

    public void navigateToRankings() {
        safeJavaScriptClick(rankingsLink);
    }
    
    public NewsPage navigateToNews() {
        safeJavaScriptClick(newsLink);
        return new NewsPage(driver, 10);
    }
}