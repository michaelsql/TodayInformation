package com.example.todayinformation.splash;


import android.os.Handler;

/**
 * Created by michael Su
 * on 2021/1/12
 */
public class CustomCountDownTimer implements Runnable{
//    private int time;
    private final CountDownHandler countDownHandler;
    private final Handler handler;
    private boolean isRun;
    private int countDownTime;


    public CustomCountDownTimer( int time, CountDownHandler countDownHandler){
//        this.time = time;
        countDownTime=time;
        this.countDownHandler=countDownHandler;
        handler = new Handler();
    }

    @Override
    public void run() {
        if (isRun){
            if (countDownHandler != null){
                countDownHandler.onTicker(countDownTime);
            }
            if (countDownTime == 0){
                cancel();
                if (countDownHandler != null){
                    countDownHandler.onFinish();
                }
            }else {
                countDownTime --;
                handler.postDelayed(this,1000);
            }
        }
    }

    public void start(){
        isRun =true;
        handler.post(this);
    }
    //跳出循环 中止
    public void cancel(){
        isRun=false;
        handler.removeCallbacks(this);
    }

    public interface CountDownHandler{
        void onTicker(int time);
        void onFinish();
    }
}
