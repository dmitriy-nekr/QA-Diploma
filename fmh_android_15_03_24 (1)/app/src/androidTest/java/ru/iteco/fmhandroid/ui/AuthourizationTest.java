package ru.iteco.fmhandroid.ui;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.R;
@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthourizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public void startTest(){
        Authorrization.checkNotAuthority();// проверка, что пользователь не авторизован, если авторизован выход из учетной записи
    }

    /*@Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);

    }


    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }*/

    @Test
    public void successAuthorizationTest() throws InterruptedException {
        Authorrization.functionEnter("login2","password2");
        Thread.sleep(5000);
        ViewInteraction imageView = onView(withId(R.id.trademark_image_view));
        imageView.check(matches(isDisplayed()));

        Authorrization.checkNotAuthority();
        Thread.sleep(3000);
        ViewInteraction textView = onView((withText("Authorization")));
        textView.check(matches(withText("Authorization")));
    }
    @Test
    public void emptyAuthorizationTest() throws InterruptedException {
        Authorrization.functionEnter(" "," ");
        ViewInteraction textView =onView(withText("Login and password cannot be empty")).inRoot(new ToastMatcher());
            textView.check(matches(isDisplayed()));
    }
    @Test
    public void wrongAuthorizationTest() throws InterruptedException {
        Authorrization.functionEnter("login","password");
        ViewInteraction textView =onView(withText("Something went wrong. Try again later.")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void emptyLoginAuthorizationTest() throws InterruptedException {
        Authorrization.functionEnter(" ","password2");
        ViewInteraction textView =onView(withText("Login and password cannot be empty")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void emptyPasswordAuthorizationTest() throws InterruptedException {
        Authorrization.functionEnter("login2"," ");
        ViewInteraction textView =onView(withText("Login and password cannot be empty")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void exchangeLoginPasswordAuthorizationTest() throws InterruptedException {
        Authorrization.functionEnter("password2","login2");
        ViewInteraction textView =onView(withText("Something went wrong. Try again later.")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void specialCharactersAuthorizationTest() throws InterruptedException {
        Authorrization.functionEnter("@login2","password2$");
        ViewInteraction textView =onView(withText("Something went wrong. Try again later.")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
    @Test
    public void differentLetterCaseAuthorizationTest() throws InterruptedException {
        Authorrization.functionEnter("lOgIN2","pASSword2");
        ViewInteraction textView =onView(withText("Something went wrong. Try again later.")).inRoot(new ToastMatcher());
        textView.check(matches(isDisplayed()));
    }
}
