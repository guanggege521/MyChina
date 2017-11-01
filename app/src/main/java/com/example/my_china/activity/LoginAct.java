package com.example.my_china.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginAct extends AppCompatActivity implements View.OnClickListener, ViewInf {

    private ImageView back_Login;
    private EditText login_userName;
    private LinearLayout login_Linear;
    private EditText login_prassWord;
    private TextView login_obtain;
    private LinearLayout Login_sign_Linear;
    private Button register_Login;

    int x = 60;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (x > 0) {
                login_obtain.setClickable(false);
                x--;
                login_obtain.setText("获取密码(" + x + ")");
                handler.postDelayed(runnable, 1000);
            } else {
                login_obtain.setClickable(true);
                login_obtain.setText("获取密码");
                x = 60;
            }
        }
    };
    private PopupWindow popupWindow;
    private LinearLayout login_Linear_Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initOnClick();
        initMob();

    }

    private void initMob() {
        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码
        // SMSSDK.setAskPermisionOnReadContact(boolShowInDialog)

        // 创建EventHandler对象
        EventHandler eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    final String msg = throwable.getMessage();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginAct.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        Log.i("login","发送成功");
                    }
                    else if(event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        Log.i("login","验证成功");
                        Intent intent=new Intent(LoginAct.this,Submitting.class);
                        intent.putExtra("username",login_userName.getText().toString());
                        startActivity(intent);
                        finish();
                       // Toast.makeText(LoginAct.this, "验证成功", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);

    }

    private void initOnClick() {
        back_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        login_userName.setOnTouchListener(new View.OnTouchListener() {
            //按住和松开的标识

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Login_sign_Linear.setBackgroundResource(R.drawable.login_ed);
                login_Linear.setBackgroundResource(R.drawable.login_two_ed);
                return false;
            }
        });

        login_prassWord.setOnTouchListener(new View.OnTouchListener() {
            //按住和松开的标识

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Login_sign_Linear.setBackgroundResource(R.drawable.login_two_ed);
                login_Linear.setBackgroundResource(R.drawable.login_ed);
                return false;
            }
        });
    }

    private void initView() {
        back_Login = (ImageView) findViewById(R.id.back_Login);
        login_userName = (EditText) findViewById(R.id.login_userName);
        login_Linear = (LinearLayout) findViewById(R.id.login_Linear);
        login_prassWord = (EditText) findViewById(R.id.login_prassWord);
        login_obtain = (TextView) findViewById(R.id.login_obtain);
        Login_sign_Linear = (LinearLayout) findViewById(R.id.Login_sign_Linear);
        register_Login = (Button) findViewById(R.id.register_Login);

        login_obtain.setOnClickListener(this);
        register_Login.setOnClickListener(this);
        login_Linear_Main = (LinearLayout) findViewById(R.id.login_Linear_Main);
        login_Linear_Main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_obtain:
                setPopupWindow();
                Presenter presenter = new Presenter(null,null,this,null,null);
                presenter.getUserOrWrod(login_userName.getText().toString());
                break;
            case R.id.register_Login:
                SMSSDK.submitVerificationCode("86",login_userName.getText().toString(),login_prassWord.getText().toString());
                break;
        }
    }


    @Override
    public void REGISTERSTAGE(String registerstarge) {
        popupWindow.dismiss();
        switch (registerstarge){
            case "用户名有误":
                handler.post(runnable);
                SMSSDK.getVerificationCode("86", login_userName.getText().toString());
                break;
            default:
                Toast.makeText(LoginAct.this, registerstarge, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void setPopupWindow() {

        View inflate = LayoutInflater.from(this).inflate(R.layout.registerpopupwindow, null);
        popupWindow = new PopupWindow(inflate, 500, 150);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(login_Linear_Main, Gravity.CENTER, 0, 0);
    }
}
