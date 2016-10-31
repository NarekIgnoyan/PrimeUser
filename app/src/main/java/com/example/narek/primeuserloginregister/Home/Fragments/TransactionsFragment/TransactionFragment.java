package com.example.narek.primeuserloginregister.Home.Fragments.TransactionsFragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.narek.primeuserloginregister.Common.Initialization;
import com.example.narek.primeuserloginregister.R;

import java.util.ArrayList;
import java.util.List;


public class TransactionFragment extends Fragment implements Initialization {
    private int myColorValue;
    private View v;
    private Button inBut,outBut;
    private TextView balanceText;

    private List<Transaction> feedsList;
    private RecyclerView mRecyclerView;
    private TransactionAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_transaction,container,false);
        initViews();

        mRecyclerView = (RecyclerView)v.findViewById(R.id.recIn);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        prepareTransactionData();
        adapter = new TransactionAdapter(getActivity(), feedsList);
        mRecyclerView.setAdapter(adapter);
        setClick();

        return v;
    }
    private void prepareTransactionData() {
        feedsList = new ArrayList<>();
        Transaction movie;

        movie = new Transaction("1. 2500");
        feedsList.add(movie);

        movie = new Transaction("2. 600");
        feedsList.add(movie);

        movie = new Transaction("3. 5000");
        feedsList.add(movie);

        movie = new Transaction("4. 500");
        feedsList.add(movie);

        movie = new Transaction("5. 7000");
        feedsList.add(movie);

        movie = new Transaction("6. 15000");
        feedsList.add(movie);

        movie = new Transaction("7. 5000");
        feedsList.add(movie);

        movie = new Transaction("8. 6000");
        feedsList.add(movie);

        movie = new Transaction("9. 1500");
        feedsList.add(movie);

        movie = new Transaction("10. 12000");
        feedsList.add(movie);

        movie = new Transaction("11. 1650");
        feedsList.add(movie);

        movie = new Transaction("12. 1800");
        feedsList.add(movie);

        movie = new Transaction("13. 2000");
        feedsList.add(movie);

        movie = new Transaction("14. 3000");
        feedsList.add(movie);

        movie = new Transaction("15. 1234");
        feedsList.add(movie);

    }


    @Override
    public void initViews() {
        inBut = (Button)v.findViewById(R.id.inButton);
        outBut = (Button)v.findViewById(R.id.outButton);

    }

    @Override
    public void initFields() {

    }

    @Override
    public void setFields() {

    }

    @Override
    public void setViews() {

    }

    public void setClick(){
        inBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomeButton();
            }
        });
        outBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outcomeButton();
            }
        });
    }

    public void incomeButton(){

        inBut.setTextColor(getIntColor("#1cbe9e"));
        outBut.setTextColor(getIntColor("#ffffff"));






    }

    public void outcomeButton(){
        inBut.setTextColor(getIntColor("#ffffff"));
        outBut.setTextColor(getIntColor("#1cbe9e"));




    }

    public int getIntColor(String s){
        myColorValue = Color.parseColor(s);
        return myColorValue;
    }
}
