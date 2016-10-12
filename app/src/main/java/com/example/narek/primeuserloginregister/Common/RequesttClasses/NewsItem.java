package com.example.narek.primeuserloginregister.Common.RequesttClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Admin on 10/12/2016.
 */

public class NewsItem {

    @JsonProperty("id")
    public int id;

    @JsonProperty("tittle_en")
    public String tittle_en;

    @JsonProperty("description_en")
    public String description_en;

    @JsonProperty("photo_url")
    public String photo_url;

    @JsonProperty("tittle_ru")
    public String tittle_ru;

    @JsonProperty("description_am")
    public String description_am;

    @JsonProperty("date")
    public String date;

    @JsonProperty("logo_url")
    public String logo_url;

    @JsonProperty("description_ru")
    public String description_ru;

    @JsonProperty("tittle_am")
    public String tittle_am;

}
