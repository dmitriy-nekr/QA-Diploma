package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

// редактирование новости
public class EditNewsScreen {
    public ViewInteraction editingNewsScreenName = onView(withText("Editing"));
    public ViewInteraction editCategoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction editTitleField = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction publicationDateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction statusSwitcher = onView(withId(R.id.switcher));


}
