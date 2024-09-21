package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.data.Helper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.Helper.waitForElement;
import static ru.iteco.fmhandroid.ui.data.Helper.withIndex;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.screenElements.MainScreenElements;

public class MainScreenSteps {
    MainScreenElements mainScreenElements = new MainScreenElements();

    public void checkMainScreenLoaded() {
        Allure.step("Загрузка страницы");
        elementWaiting(withText("News"), 10000);
    }

    public void isMainScreen()  {
        Allure.step("Проверка элементов главного экрана ");

        mainScreenElements.tradeMark.check(matches(isDisplayed()));
        mainScreenElements.news.check(matches(isDisplayed()));
        //mainScreenElements.newsUnit.check(matches(isDisplayed())); Модуль с новостями на главной странице не работает на момент сдачи работы

    }

    public void expandAllNews() {
        Allure.step("Развернуть/свернуть блок новостей");
        mainScreenElements.expandNewsFeedButton.check(matches(isDisplayed()));
        mainScreenElements.expandNewsFeedButton.perform(click());
    }

    public void allNewsNotDisplayed() {
        Allure.step("Проверка, что блок новостей свернут");
        mainScreenElements.allNewsBtn.check(matches(not(isDisplayed())));
    }

    public void allNewsDisplayed() {
        Allure.step("Проверка отображения кнопки все новости");
        mainScreenElements.allNewsBtn.check(matches(isDisplayed()));
    }



    public void expandSingleNews(int position) {
        Allure.step("Развернуть произвольно выбранную новость");
        mainScreenElements.expandSingleNews.perform(actionOnItemAtPosition(position, click()));
    }

    public void descriptionIsDisplayed(int position) {
        Allure.step("Проверка отображения описания новости");
        String descriptionText = Helper.Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
        ViewInteraction newsDescription = onView(allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        newsDescription.check(matches(isDisplayed()));
    }

    public void clickAllNews() {
        Allure.step("Кликнуть все новости");
        mainScreenElements.allNewsBtn.check(matches(isDisplayed()));
        mainScreenElements.allNewsBtn.perform(click());
    }


    public void clickOurMissionBtn() {
        Allure.step("Кликнуть кнопку наша миссия");
        mainScreenElements.ourMissionBtn.check(matches(isDisplayed()));
        mainScreenElements.ourMissionBtn.perform(click());
    }


    public void clickLogOutBtn() {
        Allure.step("Кликнуть кнопку выхода");
        mainScreenElements.logOutBtn.check(matches(isDisplayed()));
        mainScreenElements.logOutBtn.perform(click());
        onView(isRoot()).perform(waitForElement(withText("Log out"), 1000));
        mainScreenElements.logOut.check(matches(isDisplayed()));
        mainScreenElements.logOut.perform(click());
    }

    public void clickActionMenuBtn() {
        Allure.step("Кликнуть кнопку меню");
        mainScreenElements.menuBtn.check(matches(isDisplayed()));
        mainScreenElements.menuBtn.perform(click());
    }

    public void checkMenuList() {
        Allure.step("Проверка выпадающего списка кнопки меню");
        mainScreenElements.titleMain.check(matches(isDisplayed()));
        mainScreenElements.titleNews.check(matches(isDisplayed()));
        mainScreenElements.titleAbout.check(matches(isDisplayed()));
    }


    public void goToNewsScreen() {
        Allure.step("Кликнуть название экрана Новости в выпадающем списке");
        mainScreenElements.menuBtn.perform(click());
        mainScreenElements.titleNews.check(matches(isDisplayed()));
        mainScreenElements.titleNews.perform(click());
    }

    public void goToAboutScreen() {
        Allure.step("Кликнуть название экрана О приложении в выпадающем списке");
        mainScreenElements.menuBtn.perform(click());
        mainScreenElements.titleAbout.check(matches(isDisplayed()));
        mainScreenElements.titleAbout.perform(click());
    }

    public void goToMainScreen() {
        Allure.step("Кликнуть название экрана Главная в выпадающем списке");
        mainScreenElements.menuBtn.perform(click());
        mainScreenElements.titleMain.check(matches(isDisplayed()));
        mainScreenElements.titleMain.perform(click());
    }

}
