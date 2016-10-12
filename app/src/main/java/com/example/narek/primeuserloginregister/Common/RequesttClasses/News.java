package com.example.narek.primeuserloginregister.Common.RequesttClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Admin on 10/10/2016.
 */

public class News {
    @JsonProperty("news")
    public ArrayList<NewsItem> news;
}
