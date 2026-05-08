package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By PERSONAL_CABINET_HEADER_LINK =
            By.xpath("//p[contains(@class,'AppHeader_header__linkText__3q_va') and text()='Личный Кабинет']");
    private static final By MAIN_PAGE_LOGIN_BUTTON =
            By.xpath("//button[text()='Войти в аккаунт']");
    private static final By LOGIN_LINK =
            By.xpath("//a[contains(@class,'Auth_link__1fOlj') and text()='Войти']");
    private static final By EMAIL_FIELD =
            By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_FIELD =
            By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By SUBMIT_BUTTON =
            By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу входа через Войти в аккаунт")
    public LoginPage openViaMainPageLoginButton() {
        driver.get(TestConfig.MAIN_PAGE_URL);
        waitForVisibility(MAIN_PAGE_LOGIN_BUTTON);
        click(MAIN_PAGE_LOGIN_BUTTON);
        return this;
    }

    @Step("Открываем страницу входа через Личный Кабинет")
    public LoginPage openViaHeader() {
        driver.get(TestConfig.MAIN_PAGE_URL);
        waitForVisibility(PERSONAL_CABINET_HEADER_LINK);
        click(PERSONAL_CABINET_HEADER_LINK);
        return this;
    }

    @Step("Переходим на страницу входа по ссылке")
    public LoginPage openViaAnyLoginLink() {
        waitForVisibility(LOGIN_LINK);
        click(LOGIN_LINK);
        return this;
    }

    @Step("Вводим email: {email}")
    public LoginPage enterEmail(String email) {
        type(EMAIL_FIELD, email);
        return this;
    }

    @Step("Вводим пароль: {password}")
    public LoginPage enterPassword(String password) {
        type(PASSWORD_FIELD, password);
        return this;
    }

    @Step("Нажимаем на кнопку Войти")
    public void submitLoginForm() {
        click(SUBMIT_BUTTON);
    }
}