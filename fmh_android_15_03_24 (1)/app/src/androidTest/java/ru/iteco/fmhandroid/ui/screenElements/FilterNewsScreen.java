package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

// фильтр новостей на стр новости
public class FilterNewsScreen {
    public ViewInteraction filterScreenName = onView(withId(R.id.filter_news_title_text_view));
    public ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction startDateField = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public ViewInteraction endDateField = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public ViewInteraction filterBtn = onView(withId(R.id.filter_button));
    public ViewInteraction cancelBtn = onView(withId(R.id.cancel_button));
}
