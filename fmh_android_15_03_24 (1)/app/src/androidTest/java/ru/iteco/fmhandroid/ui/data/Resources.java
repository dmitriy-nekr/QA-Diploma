package ru.iteco.fmhandroid.ui.data;

import static ru.iteco.fmhandroid.ui.data.Helper.Rand.random;
import static ru.iteco.fmhandroid.ui.data.Helper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.Helper.getCurrentTime;



public class Resources {


    // Новости
    int number = random(1, 2, 3, 4, 5);
    public String newsPublicationDate = getCurrentDate();
    public String dateForNonExistentNews = "25.09.2039";
    public String newsPublicationTime = getCurrentTime();
    public String newsDescriptionLatin = "News Description" + " " + number;
    public String newsDescriptionCyr = "Описание новости" + " " + number;
    public String newsDescriptionSymbols = "#$%^@#&((*";
    public String newsDescriptionSpace = " ";
    public String newsTitleLatin = "News Title";
    public String newsTitleCyr = "Название новости" + " " + number;
    public String newsTitleSymbols = "#$%^@#&((*";
    public String newsTitleSpace = " ";


}
