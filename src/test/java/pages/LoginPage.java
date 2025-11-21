package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By PERSONAL_CABINET_HEADER_LINK = By.xpath("//p[contains(@class,'AppHeader_header__linkText__3q_va') and text()='Личный Кабинет']");
    private static final By MAIN_PAGE_LOGIN_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By LOGIN_LINK =  By.xpath("//a[contains(@class,'Auth_link__1fOlj') and text()='Войти']");
    private static final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_FIELD = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By SUBMIT_BUTTON = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openViaMainPageLoginButton() {
        driver.get(TestConfig.MAIN_PAGE_URL);
        waitForVisibility(MAIN_PAGE_LOGIN_BUTTON);
        click(MAIN_PAGE_LOGIN_BUTTON);
        return this;
    }

    public LoginPage openViaHeader() {
        driver.get(TestConfig.MAIN_PAGE_URL);
        waitForVisibility(PERSONAL_CABINET_HEADER_LINK);
        click(PERSONAL_CABINET_HEADER_LINK);
        waitForVisibility(LOGIN_LINK);
        click(LOGIN_LINK);
        return this;
    }

    public LoginPage openViaAnyLoginLink() {
        waitForVisibility(LOGIN_LINK);
        click(LOGIN_LINK);
        return this;
    }

    public LoginPage enterEmail(String email) {
        type(EMAIL_FIELD, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD_FIELD, password);
        return this;
    }

    public void submitLoginForm() {
        click(SUBMIT_BUTTON);
    }

    public void login(String email, String password) {
        enterEmail(email).enterPassword(password).submitLoginForm();
    }
}