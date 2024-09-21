package ru.iteco.fmhandroid.ui.test;



import androidx.test.ext.junit.rules.ActivityScenarioRule;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.steps.AboutUsSteps;
import ru.iteco.fmhandroid.ui.steps.AuthSteps;
import ru.iteco.fmhandroid.ui.steps.MainScreenSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AboutUsTest {

    AuthSteps authSteps = new AuthSteps();
    AboutUsSteps aboutUsSteps = new AboutUsSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> ActivityTestRule = new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {

        try {
            mainScreenSteps.checkMainScreenLoaded();
        } catch (Exception e) {
            authSteps.authWithValidData(Helper.authInfo());
            authSteps.clickSignInBtn();
        } finally {
            mainScreenSteps.checkMainScreenLoaded();
            mainScreenSteps.goToAboutScreen();
        }
    }

    @Test
    @DisplayName("Проверка элементов экрана")
    @Description("Корректность отображения всех элементов экрана О приложении")
    public void shouldCheckAboutUsScreenElements() {
        aboutUsSteps.isAboutUsScreen();
    }

    @Test
    @DisplayName("Проверка кликабельности ссылок")
    @Description("Кликабельность ссылок Политики конфиденциальности и Пользовательского соглашения")
    public void shouldCheckLinksAreClickable() {
        aboutUsSteps.privacyPolicyLinkClickable();
        aboutUsSteps.termsLinkClickable();
    }

    @Test
    @DisplayName("Кликабельность кнопки Назад")
    @Description("При нажатии на кнопку Назад происходит переход на предыдущий экран приложения")
    public void shouldCheckGoBackToPreviousScreen() {
        aboutUsSteps.isAboutUsScreen();
        aboutUsSteps.clickReturnBtn();
        mainScreenSteps.checkMainScreenLoaded();
        mainScreenSteps.isMainScreen();
    }
}