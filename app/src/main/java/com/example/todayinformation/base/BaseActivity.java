package com.example.todayinformation.base;

import android.os.Bundle;

import com.example.todayinformation.mvp.view.LifeCircleMvpActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 * Created by michael Su
 * on 2021/1/12
 */
public abstract class BaseActivity<T extends ViewBinding>  extends LifeCircleMvpActivity {
    public T viewBinding;
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        viewBinding=getViewBinding();
        setContentView(viewBinding.getRoot());
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewBinding=null;
    }

    public abstract T getViewBinding();
    protected abstract void initData();
    protected abstract void initListener();
}
