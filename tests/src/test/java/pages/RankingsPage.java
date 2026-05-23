package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class RankingsPage extends BasePage {
    private final By rankingTableRows = By.xpath("//table//tbody//tr | //div[contains(@class, 'table-body')]//a");
    private final By seasonDropdown = By.xpath("//select[contains(@id, 'season') or contains(@name, 'season')]");

    public RankingsPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    public int getVisibleRankingsCount() {
        try {
            waitForElementVisible(rankingTableRows);
            return driver.findElements(rankingTableRows).size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public void filterBySeasonDropdown(String seasonText) {
        try {
            if (!driver.findElements(seasonDropdown).isEmpty()) {
                WebElement dropdownElement = driver.findElement(seasonDropdown);
                Select select = new Select(dropdownElement);
                select.selectByVisibleText(seasonText);
            }
        } catch (Exception e) {
            System.out.println("Dropdown nem található.");
        }
    }
}