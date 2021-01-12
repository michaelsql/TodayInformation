package com.example.todayinformation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.todayinformation.databinding.ActivitySplashBinding;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding vBinding;
    private CustomCountDownTimer customCountDownTimer;

    @Override
    protected void initData( ) {

        vBinding.tvSplashTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });
        vBinding.vwPlay.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.splash));
        vBinding.vwPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared( MediaPlayer mp ) {
                mp.start();
            }
        });
        vBinding.vwPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion( MediaPlayer mp ) {
                mp.start();
            }
        });
        customCountDownTimer = new CustomCountDownTimer(5, new CustomCountDownTimer.CountDownHandler() {
            @Override
            public void onTicker( int time ) {
                vBinding.tvSplashTimer.setText(time+"秒");
            }

            @Override
            public void onFinish() {
                vBinding.tvSplashTimer.setText("跳过");
            }
        });
        customCountDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customCountDownTimer.cancel();
    }

    @Override
    public ActivitySplashBinding getViewBinding() {
        vBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        return vBinding;
    }


}
