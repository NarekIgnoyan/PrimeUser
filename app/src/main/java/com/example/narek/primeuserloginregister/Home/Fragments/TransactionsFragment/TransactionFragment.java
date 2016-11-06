package com.example.narek.primeuserloginregister.Home.Fragments.TransactionsFragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.narek.primeuserloginregister.Common.Initialization;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.NewsItem;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.TransactionItem;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.Transactions;
import com.example.narek.primeuserloginregister.Common.RetrofitJackson.IPC_Application;
import com.example.narek.primeuserloginregister.Common.RetrofitJackson.Responses;
import com.example.narek.primeuserloginregister.Home.Fragments.NewsFragment.RecyclerAdapter;
import com.example.narek.primeuserloginregister.MainPage.MainActivity;
import com.example.narek.primeuserloginregister.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TransactionFragment extends Fragment implements Initialization {
    private int myColorValue;
    private View v;
    private Button inBut,outBut;
    private TextView balanceText, progressText;
    private ProgressBar progressBar;
    // TODO: 04-Nov-16
   // private SharedPreferences pref_token;
   // private SharedPreferences.Editor editor_token;
   // public  static final String  PREFERENCE_TOKEN = "com.prime.primeuser.TOKEN";
   // private String userToken;

    private List<TransactionItem> transactionList , outTransactionList;
    private RecyclerView mRecyclerView;
    private TransactionAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_transaction,container,false);
        initViews();
        setViews();

        mRecyclerView = (RecyclerView)v.findViewById(R.id.recIn);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // TODO: 04-Nov-16  
       // userToken  = pref_token.getString("userName","Something went wrong");
       // Toast.makeText(getContext(), userToken, Toast.LENGTH_SHORT).show();

        prepareTransactionData();



        setClick();

        return v;
    }




    private void prepareTransactionData() {

        transactionList = new ArrayList<>();
        outTransactionList = new ArrayList<>();
        IPC_Application.i().w().userPayments("getUserPayments", MainActivity.testToken,null).enqueue(new Callback<Responses<Transactions>>() {
            @Override
            public void onResponse(Call<Responses<Transactions>> call, Response<Responses<Transactions>> response) {

                if(response.code()==200){
                    int bonus = 0;
                    if(response.body().message.equals("success")){



                        for(int i = 0;i<response.body().content.transactions.size();i++){
                            TransactionItem transactionItem = response.body().content.transactions.get(i);
                            if (transactionItem.type==1){
                                transactionList.add(transactionItem);
                            }
                            if (transactionItem.type==2){
                                outTransactionList.add(transactionItem);
                            }
                            bonus = bonus + response.body().content.transactions.get(i).payment_amount;

                        }
                        progressBar.setProgress(bonus);
                        progressText.setText(bonus+ "/" + progressBar.getMax());
                        Collections.reverse(transactionList);
                        Collections.reverse(outTransactionList);
                        adapter = new TransactionAdapter(getActivity(), transactionList);
                        mRecyclerView.setAdapter(adapter);


                    }
                }
            }

            @Override
            public void onFailure(Call<Responses<Transactions>> call, Throwable t) {

            }
        });

    }


    @Override
    public void initViews() {
        inBut = (Button)v.findViewById(R.id.inButton);
        outBut = (Button)v.findViewById(R.id.outButton);
        balanceText = (TextView)v.findViewById(R.id.balText);
        progressText = (TextView)v.findViewById(R.id.progressText);
        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);

    }

    @Override
    public void initFields() {

    }

    @Override
    public void setFields() {

    }

    @Override
    public void setViews() {
        progressBar.setMax(200000);

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
        adapter = new TransactionAdapter(getActivity(), transactionList);
        mRecyclerView.setAdapter(adapter);






    }

    public void outcomeButton(){
        inBut.setTextColor(getIntColor("#ffffff"));
        outBut.setTextColor(getIntColor("#1cbe9e"));
        adapter = new TransactionAdapter(getActivity(), outTransactionList);
        mRecyclerView.setAdapter(adapter);




    }

    public int getIntColor(String s){
        myColorValue = Color.parseColor(s);
        return myColorValue;
    }

//    private void managePreferences(){
//
//        pref_token = getActivity().getApplicationContext().getSharedPreferences(PREFERENCE_TOKEN, Context.MODE_PRIVATE);
//        editor_token = pref_token.edit();
//
//    }
}
