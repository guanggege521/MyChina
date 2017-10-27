package com.example.my_china.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_china.R;
import com.example.my_china.persenter.Presenter;
import com.example.my_china.view.ViewInf;

public class ForgetAct extends AppCompatActivity implements View.OnClickListener, ViewInf {

    private ImageView back_forget;
    private EditText forget_userName;
    private LinearLayout forget_Linear;
    private EditText forget_prassWord;
    private TextView forget_obtain;
    private LinearLayout forget_sign_Linear;
    private Button forget_Login;
    int x = 60;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (x > 0) {
                forget_obtain.setClickable(false);
                x--;
                forget_obtain.setText("获取密码(" + x + ")");
                handler.postDelayed(runnable, 1000);
            } else {
                forget_obtain.setClickable(true);
                forget_obtain.setText("获取密码");
                x = 60;
            }
        }
    };
    private PopupWindow popupWindow;
    private LinearLayout froget_Linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
        initOnClick();
    }

    private void initOnClick() {
        forget_prassWord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                forget_Linear.setBackgroundResource(R.drawable.login_ed);
                forget_sign_Linear.setBackgroundResource(R.drawable.login_two_ed);
                return false;
            }
        });

        forget_userName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                forget_Linear.setBackgroundResource(R.drawable.login_two_ed);
                forget_sign_Linear.setBackgroundResource(R.drawable.login_ed);
                return false;
            }
        });
        back_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        back_forget = (ImageView) findViewById(R.id.back_forget);
        forget_userName = (EditText) findViewById(R.id.forget_userName);
        forget_Linear = (LinearLayout) findViewById(R.id.forget_Linear);
        forget_prassWord = (EditText) findViewById(R.id.forget_prassWord);
        forget_obtain = (TextView) findViewById(R.id.forget_obtain);
        forget_sign_Linear = (LinearLayout) findViewById(R.id.forget_sign_Linear);
        forget_Login = (Button) findViewById(R.id.forget_Login);

        forget_obtain.setOnClickListener(this);
        forget_Login.setOnClickListener(this);
        froget_Linear = (LinearLayout) findViewById(R.id.froget_Linear);
        froget_Linear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.forget_obtain:
                setPopupWindow();
                Presenter presenter = new Presenter(null, this);
                presenter.getUserOrWrod(forget_userName.getText().toString());

                break;
            case R.id.forget_Login:

                break;
        }
    }

    private void submit() {
        // validate
        String userName = forget_userName.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String prassWord = forget_prassWord.getText().toString().trim();
        if (TextUtils.isEmpty(prassWord)) {
            Toast.makeText(this, "短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void REGISTERSTAGE(String registerstarge) {

        popupWindow.dismiss();

        if (registerstarge.equals("手机号不能为空")) {
            Toast.makeText(ForgetAct.this, registerstarge, Toast.LENGTH_SHORT).show();
        } else if (registerstarge.equals("请输入正确的手机号")) {
            //NO
            //handler.post(runnable);
            Toast.makeText(ForgetAct.this, registerstarge, Toast.LENGTH_SHORT).show();
        } else if (registerstarge.equals("用户名已被注册，可找回")) {
            handler.post(runnable);
            Toast.makeText(ForgetAct.this, registerstarge, Toast.LENGTH_SHORT).show();
        } else if (registerstarge.equals("用户名有误")) {
            Toast.makeText(ForgetAct.this, registerstarge, Toast.LENGTH_SHORT).show();
        }
    }

    private void setPopupWindow() {

        View inflate = LayoutInflater.from(this).inflate(R.layout.registerpopupwindow, null);
        popupWindow = new PopupWindow(inflate, 500, 150);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(froget_Linear, Gravity.CENTER, 0, 0);
    }
}