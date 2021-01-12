package com.example.todayinformation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 * Created by michael Su
 * on 2021/1/12
 */
public abstract class BaseActivity<T extends ViewBinding>  extends AppCompatActivity {
    public T viewBinding;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        viewBinding=getViewBinding();
        setContentView(viewBinding.getRoot());
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewBinding=null;
    }

    public abstract T getViewBinding();
    protected abstract void initData();
}
