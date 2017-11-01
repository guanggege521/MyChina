package com.example.my_china.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.my_china.R;
import com.example.my_china.homepage.home_activity.activity.Home;
import com.example.my_china.persenter.Presenter;
import com.example.my_china.view.ViewInf;

public class Submitting extends AppCompatActivity implements View.OnClickListener, ViewInf {

    private ImageView back_submitting;
    private EditText submitting_userName;
    private LinearLayout submitting_Linear;
    private EditText submitting_prassWord;
    private LinearLayout submitting_Two_Linear;
    private Button submitting_Register;
    private String username;
    private RadioButton man_Submitting;
    private RadioButton woman_Submitting;
    private RadioGroup gender_Submitting;
    private PopupWindow popupWindow;
    private LinearLayout linear_Submitting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitting);

        initView();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        initOnClick();

    }

    private void initOnClick() {
        submitting_prassWord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                submitting_Linear.setBackgroundResource(R.drawable.login_ed);
                submitting_Two_Linear.setBackgroundResource(R.drawable.login_two_ed);
                return false;
            }
        });

        submitting_userName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                submitting_Linear.setBackgroundResource(R.drawable.login_two_ed);
                submitting_Two_Linear.setBackgroundResource(R.drawable.login_ed);
                return false;
            }
        });
        back_submitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initView() {
        back_submitting = (ImageView) findViewById(R.id.back_submitting);
        submitting_userName = (EditText) findViewById(R.id.submitting_userName);
        submitting_Linear = (LinearLayout) findViewById(R.id.submitting_Linear);
        submitting_prassWord = (EditText) findViewById(R.id.submitting_prassWord);
        submitting_Two_Linear = (LinearLayout) findViewById(R.id.submitting_Two_Linear);
        submitting_Register = (Button) findViewById(R.id.submitting_Register);

        submitting_Register.setOnClickListener(this);
        man_Submitting = (RadioButton) findViewById(R.id.man_Submitting);
        man_Submitting.setOnClickListener(this);
        woman_Submitting = (RadioButton) findViewById(R.id.woman_Submitting);
        woman_Submitting.setOnClickListener(this);
        gender_Submitting = (RadioGroup) findViewById(R.id.gender_Submitting);
        gender_Submitting.setOnClickListener(this);
        linear_Submitting = (LinearLayout) findViewById(R.id.linear_Submitting);
        linear_Submitting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitting_Register:
                // 点击完成信息提交
                //username 手机号
                //name 用户名
                //word 密码
                //sex 性别

               setPopupWindow();

                boolean sex = man_Submitting.isChecked();
                String name = submitting_userName.getText().toString();
                String word = submitting_prassWord.getText().toString();

                Presenter presenter = new Presenter(null, null, null, this,null);
                presenter.getUserOrWrod(username, word, sex, name);

//                App app = App.getApp();
//                DaoSession sessionDao = app.getSessionDao();
//                ChinaUserDao chinaUserDao = sessionDao.getChinaUserDao();
//
//                ChinaUser chinaUser = new ChinaUser();
//                chinaUser.setId((long) 0);
//                chinaUser.setUsername("123456789");
//                chinaUser.setPassword("123");
//                chinaUser.setCellphone("123");
//
//                chinaUserDao.insert(chinaUser);
                break;
        }
    }

    @Override
    public void REGISTERSTAGE(String registerstarge) {
        popupWindow.dismiss();
        switch (registerstarge){
            case "注册成功":
                Intent intent=new Intent(this, Home.class);
                startActivity(intent);
                finish();
            break;
        }
    }

    private void setPopupWindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.registerpopupwindow, null);
        popupWindow = new PopupWindow(inflate, 500, 150);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(linear_Submitting, Gravity.CENTER, 0, 0);
    }
}
