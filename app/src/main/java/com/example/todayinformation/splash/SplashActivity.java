package com.example.todayinformation.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;

import android.view.View;

import com.example.todayinformation.base.BaseActivity;
import com.example.todayinformation.main.MainActivity;
import com.example.todayinformation.R;
import com.example.todayinformation.databinding.ActivitySplashBinding;

import java.io.File;



public class SplashActivity extends BaseActivity implements ISplashActivityContract.IView {
    private ActivitySplashBinding vBinding;
    private ISplashActivityContract.IPresenter timerPresenter;

    @Override
    protected void initData( ) {
        vBinding.vwPlay.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+ R.raw.splash));
        vBinding.vwPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared( MediaPlayer mp ) {
                mp.start();
            }
        });
        initTimerPresenter();


    }

    private void initTimerPresenter() {
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimer();
    }

    @Override
    public void setTvTimer(String s) {
        vBinding.tvSplashTimer.setText(s);
    }

    @Override
    protected void initListener() {
        vBinding.tvSplashTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
        vBinding.vwPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion( MediaPlayer mp ) {
                mp.start();
            }
        });

    }


    @Override
    public ActivitySplashBinding getViewBinding() {
        vBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        return vBinding;
    }


}
