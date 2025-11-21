package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class MainPageTest extends BaseTest {

    private MainPage mainPage;

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("После перехода на вкладку «Соусы» и обратно на «Булки» активна вкладка «Булки»")
    public void bunsTabBecomesActive() {
        mainPage = new MainPage(driver).open();

        mainPage.switchToSauces();
        mainPage.switchToBuns();

        assertTrue("Вкладка «Булки» должна быть активной", mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("После клика на вкладку «Соусы» она становится активной")
    public void saucesTabBecomesActive() {
        mainPage = new MainPage(driver).open();

        mainPage.switchToSauces();

        assertTrue("Вкладка «Соусы» должна быть активной", mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("После клика на вкладку «Начинки» она становится активной")
    public void fillingsTabBecomesActive() {
        mainPage = new MainPage(driver).open();

        mainPage.switchToFillings();

        assertTrue("Вкладка «Начинки» должна быть активной", mainPage.isFillingsTabActive());
    }

    @Test
    @DisplayName("Вкладка «Булки» активна по умолчанию")
    @Description("При открытии главной страницы вкладка «Булки» активна по умолчанию")
    public void bunsTabIsActiveByDefault() {
        mainPage = new MainPage(driver).open();

        assertTrue("Вкладка «Булки» должна быть активной по умолчанию", mainPage.isBunsTabActive());
    }
}