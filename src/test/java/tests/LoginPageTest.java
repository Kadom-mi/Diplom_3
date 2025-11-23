package tests;

import net.datafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.RegisterPage;
import pages.TestConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static steps.ApiClients.*;

public class LoginPageTest extends BaseTest {

    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        super.setUp();

        Faker faker = new Faker();
        user = new User(
                faker.internet().emailAddress(),
                faker.internet().password(8, 20, true, true),
                faker.name().fullName()
        );

        createUser(user)
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", is(user.getEmail().toLowerCase()))
                .body("accessToken", notNullValue());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            deleteUser(accessToken).then().statusCode(202);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    private void performLogin(LoginPage loginPage) {
        loginPage.enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .submitLoginForm();
    }

    private void waitForMainPageAndSaveToken() {
        new LoginPage(driver).waitForUrlToBe(TestConfig.MAIN_PAGE_URL);
        assertEquals(TestConfig.MAIN_PAGE_URL, driver.getCurrentUrl());
        accessToken = getAccessToken(loginUser(user));
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной странице")
    public void loginFromMainPageViaBigButton() {
        new LoginPage(driver).openViaMainPageLoginButton();
        performLogin(new LoginPage(driver));
        waitForMainPageAndSaveToken();
    }

    @Test
    @DisplayName("Вход через «Личный кабинет» в хедере")
    public void loginFromHeaderPersonalCabinet() {
        new LoginPage(driver).openViaHeader();
        performLogin(new LoginPage(driver));
        waitForMainPageAndSaveToken();
    }

    @Test
    @DisplayName("Вход через ссылку «Войти» на странице регистрации")
    public void loginFromRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        performLogin(loginPage);
        waitForMainPageAndSaveToken();
    }

    @Test
    @DisplayName("Вход через ссылку «Войти» на странице восстановления пароля")
    public void loginFromForgotPasswordPage() {
        driver.get(TestConfig.FORGOT_PASS_URL);
        new LoginPage(driver).openViaAnyLoginLink();
        performLogin(new LoginPage(driver));
        waitForMainPageAndSaveToken();
    }
}