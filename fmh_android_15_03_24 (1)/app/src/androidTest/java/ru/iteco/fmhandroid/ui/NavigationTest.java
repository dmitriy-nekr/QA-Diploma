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

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;


import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before // Выполняется перед тестами
    public void registerIdlingResources() { //Подключаемся к “счетчику”
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        Authorrization.checkAuthority();
    }

    @After // Выполняется после тестов
    public void unregisterIdlingResources() { //Отключаемся от “счетчика”
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }


    @Test
    public void menuTest()  {

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
    public void newsTest(){



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
    public void aboutTest(){

        ViewInteraction appCompatImageButton = onView(withId(R.id.main_menu_image_button));
        appCompatImageButton.perform(click());

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
    public void quotesTest(){

        ViewInteraction quoteButton = onView(withId(R.id.our_mission_image_button)).check(matches(isDisplayed()));
        quoteButton.perform(click());
        ViewInteraction textView2=onView(withText("Love is all"));
        textView2.check(matches(isDisplayed()));
    }

    @Test
    public void exitTest(){
        int counter = 0;
        while(counter==0){
            try{
                ViewInteraction appCompatImageButton = onView(withId(R.id.authorization_image_button));
                appCompatImageButton.perform(click());
                ViewInteraction materialTextView = onView((withId(android.R.id.title)));
                materialTextView.perform(click());
                counter++;
            }catch (PerformException | NoMatchingViewException ignored){
                --counter;
            }
        }

        ViewInteraction textView = onView((withText("Authorization")));
        textView.check(matches(withText("Authorization")));
        Authorrization.functionEnter("login2","password2");
    }

}



