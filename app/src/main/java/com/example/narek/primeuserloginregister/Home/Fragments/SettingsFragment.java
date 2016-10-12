package com.example.narek.primeuserloginregister.Home.Fragments;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.narek.primeuserloginregister.Common.Initialization;
import com.example.narek.primeuserloginregister.R;


public class SettingsFragment extends Fragment implements Initialization {
        private View v;
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v=inflater.inflate(R.layout.fragment_settings,container,false);

            return v;
        }

    @Override
    public void initViews() {

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
}
