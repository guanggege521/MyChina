package com.example.my_china.homepage.home_activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.my_china.R;
import com.example.my_china.homepage.home_activity.homefragment.Dongtai;
import com.example.my_china.homepage.home_activity.homefragment.Faxian;
import com.example.my_china.homepage.home_activity.homefragment.Wode;
import com.example.my_china.homepage.home_activity.homefragment.Zhonghe;

public class Home extends AppCompatActivity {

    private FrameLayout fragment_Home;
    private RadioButton xinwei;
    private RadioButton dongtan;
    private RadioButton faxian;
    private RadioButton wode;
    private FragmentManager supportFragmentManager;
    private FragmentTransaction begin;
    private RadioGroup grout_Home;

    private Zhonghe zhonghe=new Zhonghe();
    Dongtai dongtaif = new Dongtai();
    Faxian faxianf=new Faxian();
    Wode wodef=new Wode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        getBegin();


        initGroupClick();
//        init
    }

    private void initGroupClick() {
        grout_Home.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                begin = supportFragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.xinwei:
                        begin.show(zhonghe);
                        begin.hide(faxianf);
                        begin.hide(dongtaif);
                        begin.hide(wodef);
                    break;
                    case R.id.dongtan:
                        begin.hide(zhonghe);
                        begin.show(faxianf);
                        begin.hide(dongtaif);
                        begin.hide(wodef);
                        break;
                    case R.id.faxian:
                        begin.hide(zhonghe);
                        begin.hide(faxianf);
                        begin.show(dongtaif);
                        begin.hide(wodef);
                        break;
                    case R.id.wode:
                        begin.hide(zhonghe);
                        begin.hide(faxianf);
                        begin.hide(dongtaif);
                        begin.show(wodef);
                        break;
                }
                begin.commit();
            }
        });
    }

    private void getBegin() {
        supportFragmentManager = getSupportFragmentManager();
        begin = supportFragmentManager.beginTransaction();
        zhonghe = new Zhonghe();
        begin.add(R.id.fragment_Home, zhonghe);
        begin.add(R.id.fragment_Home, faxianf);
        begin.add(R.id.fragment_Home, dongtaif);
        begin.add(R.id.fragment_Home, wodef);
        begin.show(zhonghe);
        begin.hide(faxianf);
        begin.hide(dongtaif);
        begin.hide(wodef);
        begin.commit();
    }

    private void initView() {
        fragment_Home = (FrameLayout) findViewById(R.id.fragment_Home);
        xinwei = (RadioButton) findViewById(R.id.xinwei);
        dongtan = (RadioButton) findViewById(R.id.dongtan);
        faxian = (RadioButton) findViewById(R.id.faxian);
        wode = (RadioButton) findViewById(R.id.wode);
        grout_Home = (RadioGroup) findViewById(R.id.grout_Home);
    }
}
