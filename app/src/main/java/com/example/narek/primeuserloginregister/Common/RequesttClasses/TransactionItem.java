package com.example.narek.primeuserloginregister.Common.RequesttClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Narek on 31-Oct-16.
 */

public class TransactionItem {
    @JsonProperty("id")
    public int id;

    @JsonProperty("user_bonus_amount")
    public int user_bonus_amount;

    @JsonProperty("name")
    public String client_name;

    @JsonProperty("payment_amount")
    public int payment_amount;

    @JsonProperty("type")
    public int type;

    @JsonProperty("date")
    public String date;



}
