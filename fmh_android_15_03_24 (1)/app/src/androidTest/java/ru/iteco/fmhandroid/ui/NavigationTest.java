package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public void startTest(){
        Authorrization.checkAuthority();
    } // проверка авторизации

    @Test
    public void menuTest() throws InterruptedException {
            Thread.sleep(5000);
            ViewInteraction appCompatImageButton = onView(withId(R.id.main_menu_image_button));
            appCompatImageButton.perform(click());

            ViewInteraction textView = onView(
                    allOf(withId(android.R.id.title), withText("Main"),
                            isDisplayed()));
            textView.check(matches(isNotClickable()));

            ViewInteraction textView2 = onView(
                    allOf(withId(android.R.id.title), withText("News"),
                            isDisplayed()));
            textView2.check(matches(isEnabled()));

            ViewInteraction textView3 = onView(
                    allOf(withId(android.R.id.title), withText("About"),
                            isDisplayed()));
            textView3.check(matches(isEnabled()));

        }
    @Test
    public void newsTest()throws InterruptedException{

        Thread.sleep(5000);

        ViewInteraction appCompatImageButton = onView(withId(R.id.main_menu_image_button));
        appCompatImageButton.perform(click());
        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("News"),
                        isDisplayed()));
        textView.perform(click());
        ViewInteraction textView2=onView(withText("News"));
              textView2.check(matches(isDisplayed()));
    }
    @Test
    public void aboutTest()throws InterruptedException{
        Thread.sleep(5000);

        ViewInteraction appCompatImageButton = onView(withId(R.id.main_menu_image_button));
        appCompatImageButton.perform(click());
        Thread.sleep(2000);
        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("About"),
                        isDisplayed()));
        textView.perform(click());
        ViewInteraction textView2=onView(withText("Version:"));
        textView2.check(matches(isDisplayed()));
        ViewInteraction backButton = onView(withId(R.id.about_back_image_button)).check(matches(isDisplayed()));
        backButton.perform(click());
    }

    @Test
    public void quotesTest()throws InterruptedException{
        Thread.sleep(5000);
        ViewInteraction quoteButton = onView(withId(R.id.our_mission_image_button)).check(matches(isDisplayed()));
        quoteButton.perform(click());
        ViewInteraction textView2=onView(withText("Love is all"));
        textView2.check(matches(isDisplayed()));
    }

    @Test
    public void exitTest()throws InterruptedException{
        Thread.sleep(5000);
        ViewInteraction appCompatImageButton = onView(withId(R.id.authorization_image_button));
        appCompatImageButton.perform(click());
        ViewInteraction materialTextView = onView((withId(android.R.id.title)));
        materialTextView.perform(click());
        Thread.sleep(3000);
        ViewInteraction textView = onView((withText("Authorization")));
        textView.check(matches(withText("Authorization")));
        Authorrization.functionEnter("login2","password2");
    }


    }



