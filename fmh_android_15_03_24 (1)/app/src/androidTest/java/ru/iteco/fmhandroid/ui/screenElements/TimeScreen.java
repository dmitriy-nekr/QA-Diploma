package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.data.Helper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

// ввод времени
public class TimeScreen {
    public ViewInteraction manualTimeInputBtn =onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Switch to text input mode for the time input."),
                    childAtPosition(
                            childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")),
                                    4),
                            0),
                    isDisplayed()));
    public ViewInteraction inputHour = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.RelativeLayout")),
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.TextInputTimePickerView")),
                                            1)),
                            0)));

    public ViewInteraction inputMinute = onView(
            allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), allOf(withClassName(is("android.widget.RelativeLayout")),
                                    childAtPosition(
                                            withClassName(is("android.widget.LinearLayout")), withClassName(is("android.widget.TextInputTimePickerView")),
                                            1)),
                            3)));
}
