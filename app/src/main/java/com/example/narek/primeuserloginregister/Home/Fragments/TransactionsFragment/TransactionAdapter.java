package com.example.narek.primeuserloginregister.Home.Fragments.TransactionsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narek.primeuserloginregister.R;

import java.util.List;

/**
 * Created by Narek on 31-Oct-16.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.CustomViewHolder> {
private List<Transaction> feedItemList;
private Context mContext;

public TransactionAdapter(Context context, List<Transaction> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
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
        Transaction feedItem = feedItemList.get(i);

        //Render image using Picasso library


        //Setting text view title
        customViewHolder.textView.setText(feedItem.getTransaction());
        }

@Override
public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
        }

class CustomViewHolder extends RecyclerView.ViewHolder {

    protected TextView textView;

    public CustomViewHolder(View view) {
        super(view);
        this.textView = (TextView) view.findViewById(R.id.title);
    }
}
}