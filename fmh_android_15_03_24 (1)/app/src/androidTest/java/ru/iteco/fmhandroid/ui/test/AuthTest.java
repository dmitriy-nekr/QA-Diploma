package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.data.Helper.authInfo;



import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.EspressoIdlingResources;
import ru.iteco.fmhandroid.ui.steps.AuthSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.MainScreenSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {

    AuthSteps authSteps = new AuthSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();
    CommonSteps commonSteps = new CommonSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> ActivityTestRule = new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void registerIdlingResources() { //Подключаемся к “счетчику”
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }
    @Before
    public void logoutCheck()  {

            try {
                authSteps.checkAuthPageLoaded();
                authSteps.isAuthScreen();
            } catch (Exception e) {
                mainScreenSteps.clickLogOutBtn();
                authSteps.checkAuthPageLoaded();
            }
        }

    @After
    public void unregisterIdlingResources() { //Отключаемся от “счетчика”
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }


    @Test
    @DisplayName("В полях Логин и Пароль ввести невалидные данные")
    @Description("При вводе невалидных значений логина и пароля всплывает сообщение о неверных данных")
    public void shouldNotLogInWithInvalidData() {
        authSteps.authWithInvalidData(authInfo());
        authSteps.clickSignInBtn();
        commonSteps.checkWrongToast("Something went wrong. Try again later.");
    }

    @Test
    @DisplayName("Вход в личный кабинет со всеми пустыми полями")
    @Description("При попытке авторизоваться с пустыми логином и паролем пользователь не авторизуется, всплывает сообщение о незаполненных полях")
    public void shouldNotLogInWithEmptyData() {
        authSteps.clickSignInBtn();
        commonSteps.checkEmptyAuthDataToast();
    }

    @Test
    @DisplayName("Вход в личный кабинет с пустым полем Логин")
    @Description("При попытке авторизации с пустым логином пользователь не авторизуется, всплывает сообщение о незаполненом поле")
    public void shouldNotLogInWithEmptyLogin() {
        authSteps.authWithEmptyLogin(authInfo());
        authSteps.clickSignInBtn();
        commonSteps.checkEmptyAuthDataToast();
    }

    @Test
    @DisplayName("Вход в личный кабинет с пустым полем Пароль")
    @Description("При попытке авторизации с пустым паролем пользователь не авторизуется, всплывает собщение о незаполненом поле")
    public void shouldNotLogInWithEmptyPassword() {
        authSteps.authWithEmptyPass(authInfo());
        authSteps.clickSignInBtn();
        commonSteps.checkEmptyAuthDataToast();
    }


    @Test
    @DisplayName("Авторизация и выход")
    @Description("Пользователь авторизуется с валидными данными и выходит из приложения с помощью кнопки Выйти")
    public void shouldLogInAndLogOut() {
        authSteps.authWithValidData(authInfo());
        authSteps.clickSignInBtn();
        mainScreenSteps.checkMainScreenLoaded();
        mainScreenSteps.isMainScreen();
        mainScreenSteps.clickLogOutBtn();
        authSteps.isAuthScreen();
    }
    @Test
    @DisplayName("Поля Логин и Пароль поменять местами")
    @Description("При вводе невалидных значений логина и пароля всплывает сообщение о неверных данных")
    public void shouldNotLogInWithExchangeLoginAndPassword() {
        authSteps.authWithExchangedLoginPassword(authInfo());
        authSteps.clickSignInBtn();
        commonSteps.checkWrongToast("Something went wrong. Try again later.");
    }
    @Test
    @DisplayName("В полях Логин и Пароль добавить спецсимволы")
    @Description("При вводе невалидных значений логина и пароля всплывает сообщение о неверных данных")
    public void shouldNotLogInWithSpecialCharacters() {
        authSteps.authWithSpecialCharacters(authInfo());
        authSteps.clickSignInBtn();
        commonSteps.checkWrongToast("Something went wrong. Try again later.");
    }
    @Test
    @DisplayName("В Полях Логин и Пароль добавить спецсимволы")
    @Description("При вводе невалидных значений логина и пароля всплывает сообщение о неверных данных")
    public void shouldNotLogInWithDifferentLetterCase() {
        authSteps.authWithDifferentLetterCase(authInfo());
        authSteps.clickSignInBtn();
        commonSteps.checkWrongToast("Something went wrong. Try again later.");
    }

}