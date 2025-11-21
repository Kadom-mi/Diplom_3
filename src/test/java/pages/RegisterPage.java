package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private static final By NAME_FIELD = By.xpath("//label[text()='Имя']/following-sibling::input");
    private static final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_FIELD = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By PASSWORD_ERROR = By.xpath("//p[text()='Некорректный пароль' and contains(@class,'input__error')]");
    private static final By LOGIN_LINK = By.xpath("//a[@class='Auth_link__1fOlj' and @href='/login' and text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(TestConfig.REGISTER_URL);
    }

    public void enterName(String name) {
        type(NAME_FIELD, name);
    }

    public void enterEmail(String email) {
        type(EMAIL_FIELD, email);
    }

    public void enterPassword(String password) {
        type(PASSWORD_FIELD, password);
    }

    public void clickRegisterButton() {
        click(REGISTER_BUTTON);
    }

    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    public void clickLoginLink() {
        click(LOGIN_LINK);
    }

    public boolean isPasswordErrorVisible() {
        try {
            return waitForVisibility(PASSWORD_ERROR).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPasswordErrorText() {
        return isPasswordErrorVisible() ? waitForVisibility(PASSWORD_ERROR).getText() : "";
    }
}