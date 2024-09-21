package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class AuthScreenElements {
    // страница авторизации
    public ViewInteraction screenName =
            onView(allOf(withText("Authorization"), withParent(withParent(withId(R.id.nav_host_fragment)))));
    public ViewInteraction loginField =
            onView(allOf(
                    isDescendantOfA(withId(R.id.login_text_input_layout)),
                    withClassName(endsWith("EditText"))
            ));
    public ViewInteraction passField =
            onView(allOf(
                    isDescendantOfA(withId(R.id.password_text_input_layout)),
                    withClassName(endsWith("EditText"))
            ));

    public ViewInteraction signBtn =
            onView(withId(R.id.enter_button));
}
