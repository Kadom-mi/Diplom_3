package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage extends BasePage {

    private static final By BUNS_TAB =
            By.xpath("//span[text()='Булки']/ancestor::div[contains(@class,'tab_tab')]");
    private static final By SAUCES_TAB =
            By.xpath("//span[text()='Соусы']/ancestor::div[contains(@class,'tab_tab')]");
    private static final By FILLINGS_TAB =
            By.xpath("//span[text()='Начинки']/ancestor::div[contains(@class,'tab_tab')]");

    private static final String ACTIVE_TAB_CLASS = "tab_tab_type_current__2BEPc";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем главную страницу")
    public MainPage open() {
        driver.get(TestConfig.MAIN_PAGE_URL);
        return this;
    }

    private boolean isTabActive(By tabLocator) {
        WebElement tab = waitForVisibility(tabLocator);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> tab.getAttribute("class").contains(ACTIVE_TAB_CLASS));
        return tab.getAttribute("class").contains(ACTIVE_TAB_CLASS);
    }

    public boolean isBunsTabActive() {
        return isTabActive(BUNS_TAB);
    }

    public boolean isSaucesTabActive() {
        return isTabActive(SAUCES_TAB);
    }

    public boolean isFillingsTabActive() {
        return isTabActive(FILLINGS_TAB);
    }

    @Step("Переключаемся на вкладку булок")
    public void switchToBuns() {
        click(BUNS_TAB);
    }

    @Step("Переключаемся на вкладку соусов")
    public void switchToSauces() {
        click(SAUCES_TAB);
    }

    @Step("Переключаемся на вкладку начинок")
    public void switchToFillings() {
        click(FILLINGS_TAB);
    }
}