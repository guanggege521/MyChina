package com.example.my_china.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_china.R;

public class LoginAct extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_Login;
    private EditText login_userName;
    private LinearLayout login_Linear;
    private EditText login_prassWord;
    private TextView login_obtain;
    private LinearLayout Login_sign_Linear;
    private Button register_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initOnClick();

    }

    private void initOnClick() {
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_obtain:

                break;
            case R.id.register_Login:

                break;
        }
    }

    private void submit() {
        // validate
        String userName = login_userName.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        String prassWord = login_prassWord.getText().toString().trim();
        if (TextUtils.isEmpty(prassWord)) {
            Toast.makeText(this, "短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
