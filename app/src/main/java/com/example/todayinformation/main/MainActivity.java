package com.example.todayinformation.main;




import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.example.todayinformation.R;
import com.example.todayinformation.base.BaseActivity;
import com.example.todayinformation.databinding.ActivityMainBinding;
import com.example.todayinformation.main.tools.MainConstantTool;

import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity implements View.OnClickListener,IMainActivityContract.IView{


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
        initCheckListener();
        vBinding.facMainHome.setOnClickListener(this);
    }

    private void initCheckListener() {
        vBinding.rbMainShanghai.setChecked(true);
        vBinding.rgMainTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( RadioGroup group, int checkedId ) {
                if (checkedId == mPresenter.getCurrentCheckedId()){
                    return;
                }
                switch (checkedId){
                    case R.id.rb_main_shanghai:
                        mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
                        break;
                    case R.id.rb_main_hangzhou:
                        mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
                        break;
                }
            }
        });
        vBinding.rgMainBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( RadioGroup group, int checkedId ) {
                if (checkedId == mPresenter.getCurrentCheckedId()){
                    return;
                }
                switch (checkedId){
                    case R.id.rb_main_beijing:
                        mPresenter.replaceFragment(MainConstantTool.BEIJING);
                        break;
                    case R.id.rb_main_shenzhen:
                        mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick( View v ) {
        switch (v.getId()){
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom){
                    changeAnima(vBinding.rgMainTop,vBinding.rgMainBottom);
                    handlerTopPostion();
                }else {
                    changeAnima(vBinding.rgMainBottom,vBinding.rgMainTop);
                    handlerBottomPostion();
                }

                break;
        }
    }
    //北京 深圳
    private void handlerBottomPostion() {
        if(mPresenter.getTopPostion() != MainConstantTool.HANGZHOU){
            mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
            vBinding.rbMainShanghai.setChecked(true);
        }else {
            mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
            vBinding.rbMainHangzhou.setChecked(true);
        }
    }

    //上海 杭州
    private void handlerTopPostion() {
        if(mPresenter.getBottomPostion() != MainConstantTool.SHENZHEN){
            mPresenter.replaceFragment(MainConstantTool.BEIJING);
            vBinding.rbMainBeijing.setChecked(true);
        }else {
            mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
            vBinding.rbMainShenzhen.setChecked(true);
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

    @Override
    public void showFragment( Fragment mFragment ) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void addFragment( Fragment mFragment ) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content,mFragment).commit();
    }

    @Override
    public void hideFragment( Fragment mFragment ) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }



}