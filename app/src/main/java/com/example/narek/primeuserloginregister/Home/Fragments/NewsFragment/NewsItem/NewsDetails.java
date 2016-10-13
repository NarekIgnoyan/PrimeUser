package com.example.narek.primeuserloginregister.Home.Fragments.NewsFragment.NewsItem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.narek.primeuserloginregister.Common.Initialization;
import com.example.narek.primeuserloginregister.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsDetails extends AppCompatActivity implements Initialization {
    private ImageView image;
    private TextView title;
    private TextView body;
    private ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        initViews();
        setViews();

    }

    @Override
    public void initViews() {
        image =(ImageView)findViewById(R.id.backgroundImage);
        title = (TextView) findViewById(R.id.title);
        body = (TextView) findViewById(R.id.body);
        data = getIntent().getExtras().getStringArrayList("data");
    }

    @Override
    public void initFields() {

    }

    @Override
    public void setFields() {

    }

    @Override
    public void setViews() {
        Picasso.with(this).load(data.get(0)).into(image);
        title.setText(data.get(1));
        body.setText(data.get(2));

    }
}
