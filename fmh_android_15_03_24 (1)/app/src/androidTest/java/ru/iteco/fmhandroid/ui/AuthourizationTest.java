package ru.iteco.fmhandroid.ui;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;


import io.qameta.allure.android.runners.AllureAndroidJUnit4;

import io.qameta.allure.android.runners.AllureAndroidJUnitRunner;
import io.qameta.allure.kotlin.junit4.AllureRunner;
import ru.iteco.fmhandroid.R;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthourizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @Before // Выполняется перед тестами
    public void registerIdlingResources() { //Подключаемся к “счетчику”
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        Authorrization.checkNotAuthority();
    }

    @After // Выполняется после тестов
    public void unregisterIdlingResources() { //Отключаемся от “счетчика”
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }


    @Test
    public void successAuthorizationTest()  {

        Authorrization.functionEnter("login2","password2");
        int counter = 0;
        while (counter==0){
            try{
                ViewInteraction appCompatImageButton = onView(withId(R.id.authorization_image_button));
                appCompatImageButton.check(matches(isDisplayed()));
                counter++;
            }catch (AssertionError ignored){
                --counter;
            }
        }

        Authorrization.checkNotAuthority();
        ViewInteraction textView = onView((withText("Authorization")));
        textView.check(matches(withText("Authorization")));
    }


    @Test
    public void emptyAuthorizationTest()  {
        Authorrization.functionEnter(" "," ");
        ViewInteraction textView =onView(withText("Login and password cannot be empty")).inRoot(new ToastMatcher());
            textView.check(matches(isDisplayed()));
    }
    @Test
    public void wrongAuthorizationTest()  {
        Authorrization.functionEnter("login","password");
        ViewInteraction textView =onView(withText("Something went wrong. Try again later.")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void emptyLoginAuthorizationTest() {
        Authorrization.functionEnter(" ","password2");
        ViewInteraction textView =onView(withText("Login and password cannot be empty")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void emptyPasswordAuthorizationTest()  {
        Authorrization.functionEnter("login2"," ");
        ViewInteraction textView =onView(withText("Login and password cannot be empty")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void exchangeLoginPasswordAuthorizationTest() {
        Authorrization.functionEnter("password2","login2");
        ViewInteraction textView =onView(withText("Something went wrong. Try again later.")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void specialCharactersAuthorizationTest()  {
        Authorrization.functionEnter("@login2","password2$");
        ViewInteraction textView =onView(withText("Something went wrong. Try again later.")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void differentLetterCaseAuthorizationTest()  {
        Authorrization.functionEnter("lOgIN2","pASSword2");
        ViewInteraction textView =onView(withText("Something went wrong. Try again later.")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }


}
