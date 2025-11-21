package tests;

import net.datafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.RegisterPage;
import pages.TestConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static steps.ApiClients.*;

public class RegisterPageTest extends BaseTest {

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
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Позитивный сценарий: корректные данные → редирект на страницу входа")
    public void successfulRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.enterName(user.getName());
        registerPage.enterEmail(user.getEmail());
        registerPage.enterPassword(user.getPassword());
        registerPage.clickRegisterButton();

        registerPage.waitForUrlToBe(TestConfig.LOGIN_URL);

        assertEquals(TestConfig.LOGIN_URL, driver.getCurrentUrl());

        accessToken = getAccessToken(loginUser(user));
    }

    @Test
    @DisplayName("Ошибка при пароле короче 6 символов")
    @Description("Негативный сценарий: пароль < 6 символов → сообщение «Некорректный пароль» и остаёмся на /register")
    public void registrationWithShortPasswordShowsError() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.enterName(user.getName());
        registerPage.enterEmail(user.getEmail());
        registerPage.enterPassword("12345");
        registerPage.clickRegisterButton();

        assertEquals(TestConfig.REGISTER_URL, driver.getCurrentUrl());
        assertTrue("Должно отображаться сообщение об ошибке пароля", registerPage.isPasswordErrorVisible());
        assertEquals("Некорректный пароль", registerPage.getPasswordErrorText());
    }
}