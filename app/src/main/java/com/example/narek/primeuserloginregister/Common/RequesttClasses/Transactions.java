package com.example.narek.primeuserloginregister.Common.RequesttClasses;

import com.example.narek.primeuserloginregister.Home.Fragments.TransactionsFragment.TransactionFragment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Narek on 04-Nov-16.
 */

public class Transactions {
    @JsonProperty("payments")
    public ArrayList<TransactionItem> transactions;
}
