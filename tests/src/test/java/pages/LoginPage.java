package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    
    // Visszaálltunk a robusztusabb, de még mindig komplex XPath-ra, ami több opciót is lefed
    private final By emailInput = By.xpath("//input[@type='email' or @type='text' or @name='username' or @id='username']");
    private final By passwordInput = By.xpath("//input[@type='password' or @name='password' or @id='password']");
    private final By submitButton = By.xpath("//button[@type='submit'] | //input[@type='submit']");
    private final By errorMessage = By.xpath("//*[contains(@class, 'error') or contains(@class, 'alert') or contains(text(), 'invalid') or contains(text(), 'Incorrect')]");

    public LoginPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    public void login(String email, String password) {
        writeText(emailInput, email);
        writeText(passwordInput, password);
        click(submitButton);
    }

    public String getErrorMessageText() {
        return readText(errorMessage);
    }
}