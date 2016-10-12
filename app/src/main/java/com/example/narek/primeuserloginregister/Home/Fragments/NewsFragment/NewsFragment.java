package com.example.narek.primeuserloginregister.Home.Fragments.NewsFragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.narek.primeuserloginregister.Common.Initialization;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.Login;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.News;
import com.example.narek.primeuserloginregister.Common.RequesttClasses.NewsItem;
import com.example.narek.primeuserloginregister.Common.RetrofitJackson.IPC_Application;
import com.example.narek.primeuserloginregister.Common.RetrofitJackson.Responses;
import com.example.narek.primeuserloginregister.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements Initialization{
        private View v;
        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        private ArrayList<NewsItem> names;
    @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v=inflater.inflate(R.layout.fragment_news,container,false);
            initFields();
            initViews();
            setFields();
            setViews();
            getDataFromServer();
            return v;
        }


    @Override
    public void initViews() {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.chooseRecycle);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void initFields() {
        names = new ArrayList<>();
    }

    @Override
    public void setFields() {
        fillNames();
    }

    @Override
    public void setViews() {
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void fillNames() {
    }

    public void getDataFromServer() {

        IPC_Application.i().w().news("getNews",null).enqueue(new Callback<Responses<News>>() {
            @Override
            public void onResponse(Call<Responses<News>> call, Response<Responses<News>> response) {

                if(response.code()==200){
                    if(response.body().message.equalsIgnoreCase("success")){


                        for(int i = 0;i<response.body().content.news.size();i++){
                            NewsItem news = response.body().content.news.get(i);
                            names.add(news);
                        }


                        mAdapter = new RecyclerAdapter(names,getActivity());
                        mRecyclerView.setAdapter(mAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Responses<News>> call, Throwable t) {
                Toast.makeText(getActivity(), " :  " +t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
