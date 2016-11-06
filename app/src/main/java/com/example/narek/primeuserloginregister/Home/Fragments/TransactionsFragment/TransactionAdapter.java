package com.example.narek.primeuserloginregister.Home.Fragments.TransactionsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narek.primeuserloginregister.Common.RequesttClasses.TransactionItem;
import com.example.narek.primeuserloginregister.R;

import java.util.List;

/**
 * Created by Narek on 31-Oct-16.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.CustomViewHolder> {
    private List<TransactionItem> transactionItems;
    private Context mContext;
    private String normDate;

    public TransactionAdapter(Context context, List<TransactionItem> transactionItems) {
        this.transactionItems = transactionItems;
        this.mContext = context;
        Log.e("banman", "TransactionAdapter: " + transactionItems.size());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trans_list_row, null);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_list_row, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        TransactionItem feedItem = transactionItems.get(i);

        //Render image using Picasso library


        //Setting text view title
        if (feedItem.type==2){
            customViewHolder.cafeName.setText("" + feedItem.client_name + "  -");
            customViewHolder.bonusAmount.setText("" + feedItem.payment_amount);
            normDate = feedItem.date.substring(0, feedItem.date.length() - 2);
            customViewHolder.bonusDate.setText("" + normDate);
        }else {
            customViewHolder.cafeName.setText("" + feedItem.client_name + "  -");
            customViewHolder.bonusAmount.setText("" + feedItem.user_bonus_amount);
            normDate = feedItem.date.substring(0, feedItem.date.length() - 2);
            customViewHolder.bonusDate.setText("" + normDate);
        }


    }

    @Override
    public int getItemCount() {
        return (null != transactionItems ? transactionItems.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView cafeName, bonusAmount, bonusDate;

        public CustomViewHolder(View view) {
            super(view);
            this.cafeName = (TextView) view.findViewById(R.id.cafeName);
            this.bonusAmount = (TextView) view.findViewById(R.id.bonusAmount);
            this.bonusDate = (TextView) view.findViewById(R.id.bonusDate);
        }
    }
}