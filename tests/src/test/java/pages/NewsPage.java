package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewsPage extends BasePage {
    private final By mainHeader = By.xpath("//h1[contains(@class, 'title') or contains(@class, 'header')]"); // Komplex

    public NewsPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    public String getMainHeaderText() {
        return readText(mainHeader);
    }
}