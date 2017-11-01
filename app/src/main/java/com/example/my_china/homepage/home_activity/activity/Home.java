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
    private Zhonghe zhonghe;
    Dongtai dongtaif = null;
    Faxian faxianf=null;
    Wode wodef=null;

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

                switch (checkedId){
                    case R.id.xinwei:
                        if(zhonghe!=null){
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_Home,zhonghe).commit();
                        }else{
                            zhonghe=new Zhonghe();
                            supportFragmentManager.beginTransaction().replace(R.id.fragment_Home,zhonghe).commit();
                        }
                    break;
                    case R.id.dongtan:
                        if(dongtaif!=null){
                            supportFragmentManager.beginTransaction().replace(R.id.fragment_Home,dongtaif).commit();
                        }else{
                            dongtaif=new Dongtai();
                            supportFragmentManager.beginTransaction().replace(R.id.fragment_Home,dongtaif).commit();
                        }
                        break;
                    case R.id.faxian:
                        if(faxianf!=null){
                            supportFragmentManager.beginTransaction().replace(R.id.fragment_Home,faxianf).commit();
                        }else{
                            faxianf=new Faxian();
                            supportFragmentManager.beginTransaction().replace(R.id.fragment_Home,faxianf).commit();
                        }
                        break;
                    case R.id.wode:
                        if(wodef!=null){
                            supportFragmentManager.beginTransaction().replace(R.id.fragment_Home,wodef).commit();
                        }else{
                            wodef=new Wode();
                            supportFragmentManager.beginTransaction().replace(R.id.fragment_Home,wodef).commit();
                        }
                        break;
                }
            }
        });
    }

    private void getBegin() {
        supportFragmentManager = getSupportFragmentManager();
        begin = supportFragmentManager.beginTransaction();
        zhonghe = new Zhonghe();
        begin.add(R.id.fragment_Home, zhonghe);
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
