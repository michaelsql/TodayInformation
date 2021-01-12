package com.example.todayinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;

import com.example.todayinformation.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {


    private ActivityMainBinding vBinding;

    @Override
    public ActivityMainBinding getViewBinding() {
        vBinding = ActivityMainBinding.inflate(getLayoutInflater());
        return vBinding;
    }

    @Override
    protected void initData() {

    }
}