package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class Authorrization {
    public static void functionEnter(String login, String password) throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction textInputEditText = onView(allOf(
                        isDescendantOfA(withId(R.id.login_text_input_layout)),
                        withClassName(endsWith("EditText"))
                )
        ).check(matches(isDisplayed()));

        textInputEditText.perform(typeText(login), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(allOf(
                        isDescendantOfA(withId(R.id.password_text_input_layout)),
                        withClassName(endsWith("EditText"))
                )
        ).check(matches(isDisplayed()));

        textInputEditText2.perform(typeText(password), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

    }
    public static void checkAuthority() {
        try {
            functionEnter("login2", "password2");
        } catch (NoMatchingViewException | InterruptedException ignored) {
        }
    }
    public static void checkNotAuthority() {
        try {
            Thread.sleep(5000);
            ViewInteraction appCompatImageButton = onView(withId(R.id.authorization_image_button));
            appCompatImageButton.perform(click());
            ViewInteraction materialTextView = onView((withId(android.R.id.title)));
            materialTextView.perform(click());
        } catch (NoMatchingViewException | InterruptedException | PerformException ignored) {
        }
    }
}


