package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static ru.iteco.fmhandroid.ui.data.Helper.elementWaiting;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.screenElements.CommonElements;
import ru.iteco.fmhandroid.ui.screenElements.TimeScreen;

public class CommonSteps {
    CommonElements commonElements = new CommonElements();
    TimeScreen timeScreen = new TimeScreen();

    public void clickSave() {
        Allure.step("Кликнуть Сохранить");
        commonElements.saveBtn.perform(click());
    }

    public void clickCancel() {
        Allure.step("Кликнуть Отмена");
        commonElements.cancelBtn.perform(click());
    }

    public void clickOkBtn() {
        Allure.step("Кликнуть ок");
        commonElements.okBtn.perform(click());
    }

    public void clickCancelInDialog() {
        Allure.step("Кликнуть Отмена в диалоговом окне");
        commonElements.cancelInDialog.perform(click());
    }



    public void checkEmptyToast(int id, boolean visible) {
        if (visible) {
            commonElements.emptyToast(id).check(matches(isDisplayed()));
        } else {
            commonElements.emptyToast(id).check(matches(not(isDisplayed())));
        }
    }


    public void checkWrongAuthDataToast() {
        Allure.step("Проверка предупреджения");
        checkEmptyToast(R.string.wrong_login_or_password, true);
    }

    public void checkEmptyAuthDataToast() {
        Allure.step("Проверка предупреджения");
        checkEmptyToast(R.string.empty_login_or_password, true);
    }
    public void checkWrongToast(String text) {
        Allure.step("Проверка предупреджения");
        commonElements.wrongToast(text).check(matches(isDisplayed()));
    }

    public void checkErrorToast(int id, boolean visible) {
        if (visible) {
            commonElements.errorToast(id).check(matches(isDisplayed()));
        } else {
            commonElements.errorToast(id).check(matches(not(isDisplayed())));
        }
    }

    public void checkEmptyFieldError() {
        Allure.step("Проверка текста тоста");
        checkErrorToast(R.string.empty_fields, true);
    }

    public void checkWrongData(String text, boolean visible) {
        if (visible) {
            commonElements.wrongData(text).check(matches(isDisplayed()));
        } else {
            commonElements.wrongData(text).check(matches(not(isDisplayed())));
        }
    }

    public void checkNewsButterflyImage() {
        Allure.step("Проверка картинки с бабочкой страницы новости");
        commonElements.butterflyImageNews.check(matches(isDisplayed()));
    }

    public void checkControlPanelButterflyImage() {
        Allure.step("Провекра картинки с бабочкой раздела панель управления");
        commonElements.butterflyImageControlPanel.check(matches(isDisplayed()));
    }

    public void checkNothingToShowScreen() {
        Allure.step("Проверка элементов экрана ничего не найдено");
        commonElements.nothingToShowWarning.check(matches(isDisplayed()));
        commonElements.refreshBtn.check(matches(isDisplayed()));
    }

    public void manualTimeInput(String hour, String minute) {
        Allure.step("Ввести время вручную");
        timeScreen.manualTimeInputBtn.perform(click());
        timeScreen.inputHour.perform(replaceText(hour));
        timeScreen.inputMinute.perform(replaceText(minute));
        clickOkBtn();
    }

    public void checkInvalidTimeError() {
        Allure.step("Проверка предупреждения о невалидном значении времени");
        elementWaiting(withText("Enter a valid time"), 10000);
        commonElements.wrongTimeError.check(matches(isDisplayed()));
    }
}
