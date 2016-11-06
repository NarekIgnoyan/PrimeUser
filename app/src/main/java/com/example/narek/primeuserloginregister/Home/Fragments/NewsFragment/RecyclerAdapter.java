package com.example.narek.primeuserloginregister.Home.Fragments.NewsFragment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.narek.primeuserloginregister.Common.RequesttClasses.NewsItem;
import com.example.narek.primeuserloginregister.Home.Fragments.NewsFragment.NewsItem.NewsDetails;
import com.example.narek.primeuserloginregister.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Admin on 9/13/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<NewsItem> names;
    private Context context;


    public RecyclerAdapter(ArrayList<NewsItem> names, Context context){
        this.names = names;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mLogo;
        public TextView mTextView;
        public LinearLayout layoutItem;
        public ViewHolder(View v) {
            super(v);
            this.mLogo = (ImageView) v.findViewById(R.id.logo);
            this.mTextView =(TextView) v.findViewById(R.id.title);
            this.layoutItem = (LinearLayout)v.findViewById(R.id.rececleLayout);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row_animation_card_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(names.get(position).logo_url).into(holder.mLogo);
        holder.mTextView.setText(names.get(position).tittle_en);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NewsDetails.class);
                ArrayList<String> strings = new ArrayList<String>();
                strings.add(names.get(position).photo_url);
                strings.add(names.get(position).tittle_en);
                strings.add(names.get(position).description_en);
                intent.putExtra("data",strings);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

}
