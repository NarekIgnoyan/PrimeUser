package com.example.narek.primeuserloginregister.Common.RequesttClasses;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by Narek on 23-Sep-16.
 */
public class Login {

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("sex")
    public int sex;

    @JsonProperty("primeCardNumber")
    public String primeCardNumber;

    @JsonProperty("confirmCode")
    public String confirmCode;

    @JsonProperty("country")
    public String country;

    @JsonProperty("specialDay")
    public String specialDay;

    @JsonProperty("adress")
    public String adress;

    @JsonProperty("registrationDate")
    public String registrationDate;

    @JsonProperty("primeCardType")
    public String primeCardType;

    @JsonProperty("postalCode")
    public String postalCode;

    @JsonProperty("phoneNumber")
    public String phoneNumber;

    @JsonProperty("birthdayDate")
    public String birthdayDate;

    @JsonProperty("token")
    public String token;

    @JsonProperty("user_id")
    public String user_id;

    @JsonProperty("language")
    public String Ulanguage;

    @JsonProperty("firstName")
    public String firstName;



}
