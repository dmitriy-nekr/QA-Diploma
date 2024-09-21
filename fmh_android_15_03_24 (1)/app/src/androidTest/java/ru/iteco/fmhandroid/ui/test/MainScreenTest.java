package ru.iteco.fmhandroid.ui.test;



import android.os.SystemClock;

import androidx.test.espresso.PerformException;
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
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.steps.OurMissionSteps;

@RunWith(AllureAndroidJUnit4.class)
public class MainScreenTest {
    AuthSteps authSteps = new AuthSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    NewsSteps newsSteps = new NewsSteps();
    AboutUsSteps aboutUsSteps = new AboutUsSteps();
    OurMissionSteps ourMissionSteps = new OurMissionSteps();


    @Rule
    public ActivityScenarioRule<AppActivity> ActivityTestRule = new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(8000);
        try {
            mainScreenSteps.checkMainScreenLoaded();
        } catch (PerformException e) {
            authSteps.authWithValidData(Helper.authInfo());
            authSteps.clickSignInBtn();
        } finally {
            mainScreenSteps.checkMainScreenLoaded();
        }
    }

    @Test
    @DisplayName("Проверка элементов экрана")
    @Description("Проверка корректности отображения всех элементов Главного экарана")
    public void shouldCheckMainScreenElements() {
        mainScreenSteps.isMainScreen();
    }

    @Test
    @DisplayName("Проверка списка вкладок кнопки меню")
    @Description("При нажатии на кнопку меню в выпадающем списке есть разделы Главная,  Новости, О приложении")
    public void shouldCheckActionMenuScreenList() {
        mainScreenSteps.clickActionMenuBtn();
        mainScreenSteps.checkMenuList();
    }

    @Test
    @DisplayName("Переход по вкладкам с помощью кнопки меню")
    @Description("При нажатии на название экрана в выпадающем списке кнопки меню, пользователь переходит на соответствующую страницу приложения")
    public void shouldCheckTransitionToScreensViaMenuBtn() {


        mainScreenSteps.isMainScreen();
        mainScreenSteps.goToAboutScreen();
        aboutUsSteps.isAboutUsScreen();
        aboutUsSteps.clickReturnBtn();
        mainScreenSteps.goToNewsScreen();
        newsSteps.isNewsScreen();

    }

    @Test
    @DisplayName("Переход на страницу Наша миссия")
    @Description("При нажатии на кнопку в виде бабочки пользователь переходит на страницу Наша миссия")
    public void shouldCheckTransitionToOurMissionScreen() {
        mainScreenSteps.clickOurMissionBtn();
        ourMissionSteps.isOurMissionScreen();
    }

    @Test
    @DisplayName("Выход нажатием кнопки Выйти")
    @Description("При нажатии на кнопку в виде человечка пользователь может выйти из приложения")
    public void shouldCheckLogOut() {
        mainScreenSteps.clickLogOutBtn();
        authSteps.isAuthScreen();
    }

    @Test
    @DisplayName("Кликабельность кнопки Все новости")
    @Description("При нажатии на странице основного экрана кнопки Все новости пользователь переходит на вкладку Новости и может вернуться на оснвоной экран")
    public void shouldCheckAllNewsBtn() {
        mainScreenSteps.clickAllNews();
        newsSteps.isNewsScreen();
        mainScreenSteps.goToMainScreen();
        mainScreenSteps.isMainScreen();
    }

    @Test
    @DisplayName("Кликабельность кнопки развернуть/свернуть список новостей")
    @Description("При нажатии на блок новостей новости сворачиваются, при повторном нажатии - разворачиваются")
    public void shouldShowOrHideNewsBlock() {
        mainScreenSteps.expandAllNews();
        mainScreenSteps.allNewsNotDisplayed();
        mainScreenSteps.expandAllNews();
        mainScreenSteps.allNewsDisplayed();
    }


    @Test
    @DisplayName("Кликабельность кнопок свернуть/развернуть в каждой Новости главного меню")
    @Description("При нажатии на отдельную новость разворачивается ее содержание")
    public void shouldExpandAndHideSingleNews() {
        int position = 0;
        mainScreenSteps.expandSingleNews(position);
        mainScreenSteps.descriptionIsDisplayed(position);
    }

}


