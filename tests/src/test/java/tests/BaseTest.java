package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigReader;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    protected WebDriver driver;
    protected ConfigReader config;
    protected String baseUrl;
    protected int timeout;

    @Before
    public void setUp() throws Exception {
        config = new ConfigReader();
        baseUrl = config.getProperty("base.url", "https://www.wst.tv");
        timeout = Integer.parseInt(config.getProperty("explicit.wait.seconds", "10"));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--headless"); // Headless pont pipa

        String remoteUrl = "http://selenium:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(remoteUrl), options);
        
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}