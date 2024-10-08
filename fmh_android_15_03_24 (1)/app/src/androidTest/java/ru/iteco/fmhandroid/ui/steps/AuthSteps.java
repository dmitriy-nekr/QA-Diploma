package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.Helper.differentLetterCase;
import static ru.iteco.fmhandroid.ui.data.Helper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.Helper.emptyLogin;
import static ru.iteco.fmhandroid.ui.data.Helper.emptyPassword;
import static ru.iteco.fmhandroid.ui.data.Helper.exchangeLoginPassword;
import static ru.iteco.fmhandroid.ui.data.Helper.invalidAuthData;
import static ru.iteco.fmhandroid.ui.data.Helper.specialCharactersLoginPassword;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.AuthScreenElements;

public class AuthSteps {
    AuthScreenElements authScreenElements = new AuthScreenElements();

    public void checkAuthPageLoaded() {
        Allure.step("Загрузка страницы авторизации");
        elementWaiting(withId(R.id.enter_button), 8000);
    }

    public void isAuthScreen() {
        Allure.step("Проверка элементов экрана авторизации");
        authScreenElements.screenName.check(matches(isDisplayed()));
        authScreenElements.loginField.check(matches(isDisplayed()));
        authScreenElements.passField.check(matches(isDisplayed()));
        authScreenElements.signBtn.check(matches(isDisplayed()));
    }

    public void authWithValidData(Helper.AuthInfo info) {
        Allure.step("Авторизация с валидными данными");
        isAuthScreen();
        authScreenElements.loginField.perform(replaceText(info.getLogin()));
        authScreenElements.passField.perform(replaceText(info.getPass()));
    }

    public void authWithInvalidData(Helper.AuthInfo info) {
        Allure.step("Авторизация с невалидными данными");
        isAuthScreen();
        authScreenElements.loginField.perform(replaceText(invalidAuthData().getLogin()));
        authScreenElements.passField.perform(replaceText(invalidAuthData().getPass()));
    }
    public void authWithExchangedLoginPassword(Helper.AuthInfo info) {
        Allure.step("Авторизация с паролем и логином которые поменяли местами");
        authScreenElements.loginField.perform(replaceText(exchangeLoginPassword().getLogin()));
        authScreenElements.passField.perform(replaceText(exchangeLoginPassword().getPass()));
    }
    public void authWithSpecialCharacters(Helper.AuthInfo info) {
        Allure.step("Авторизация со спецсимволами");
        authScreenElements.loginField.perform(replaceText(specialCharactersLoginPassword().getLogin()));
        authScreenElements.passField.perform(replaceText(specialCharactersLoginPassword().getPass()));
    }
    public void authWithDifferentLetterCase(Helper.AuthInfo info) {
        Allure.step("Авторизация с разным регистром символом");
        authScreenElements.loginField.perform(replaceText(differentLetterCase().getLogin()));
        authScreenElements.passField.perform(replaceText(differentLetterCase().getPass()));
    }

    public void authWithEmptyPass(Helper.AuthInfo info) {
        Allure.step("Авторизация с пустым паролем");
        authScreenElements.loginField.perform(replaceText(emptyPassword().getLogin()));
        authScreenElements.passField.perform(replaceText(emptyPassword().getPass()));
    }

    public void authWithEmptyLogin(Helper.AuthInfo info) {
        Allure.step("Авторизация с пустым логином");
        authScreenElements.loginField.perform(replaceText(emptyLogin().getLogin()));
        authScreenElements.passField.perform(replaceText(emptyLogin().getPass()));
    }

    public void clickSignInBtn() {
        Allure.step("Нажать кнопку Войти");
        authScreenElements.signBtn.perform(click());
    }

}
