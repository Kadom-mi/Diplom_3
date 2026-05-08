package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    protected WebDriver driver;

    private static final String BROWSER = System.getProperty("browser", "chrome").toLowerCase();

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--disable-features=TranslateUI");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");

        if ("yandex".equals(BROWSER)) {
            System.setProperty
                    ("webdriver.chrome.driver", "C:\\Users\\Vrabshe\\Downloads\\yandexdriver\\yandexdriver.exe");
            String yandexBinary =
                    "C:\\Users\\Vrabshe\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
            options.setBinary(yandexBinary);
        }
        else {
            WebDriverManager.chromedriver().setup();
        }
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}