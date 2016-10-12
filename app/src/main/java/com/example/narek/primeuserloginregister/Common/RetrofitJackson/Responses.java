package com.example.narek.primeuserloginregister.Common.RetrofitJackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Responses<T> {

    @JsonProperty("status")
    public int status;

    @JsonProperty("message")
    public String message;

    @JsonProperty("content")
    public T content;

}
