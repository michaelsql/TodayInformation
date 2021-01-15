package com.example.todayinformation.main;




import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;

import com.example.todayinformation.R;
import com.example.todayinformation.base.BaseActivity;
import com.example.todayinformation.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements View.OnClickListener ,IMainActivityContract.IView{


    private ActivityMainBinding vBinding;
    private boolean isChangeTopOrBottom=false;
    private IMainActivityContract.IPresenter mPresenter =  new MainActivityPresenter(this);
    @Override
    public ActivityMainBinding getViewBinding() {
        vBinding = ActivityMainBinding.inflate(getLayoutInflater());

        return vBinding;
    }


    @Override
    protected void initData() {
        initHomeFragment();
        changeAnima(vBinding.rgMainBottom,vBinding.rgMainTop);

    }

    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }

    @Override
    protected void initListener() {
        vBinding.facMainHome.setOnClickListener(this);
    }

    @Override
    public void onClick( View v ) {
        switch (v.getId()){
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom){
                    changeAnima(vBinding.rgMainTop,vBinding.rgMainBottom);
                }else {
                    changeAnima(vBinding.rgMainBottom,vBinding.rgMainTop);
                }
                break;
        }
    }

    private void changeAnima( RadioGroup gone,RadioGroup show ) {
        //消失的动画
        gone.clearAnimation();
        Animation animationGone = AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }
}