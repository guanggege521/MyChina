package com.example.my_china.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

public class RegisterAct extends AppCompatActivity implements View.OnClickListener, ViewInf {

    private ImageView back_register;
    private Toolbar tb_register;
    private EditText reginst_userName;
    private EditText reginst_prassWord;
    private TextView register_Forget;
    private Button register_Register;
    private Button Register_Login;
    private LinearLayout register_Linear;
    private LinearLayout sign_Linear;
    private LinearLayout linear_Register;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initOnclick();
    }

    private void initOnclick() {

        reginst_userName.setOnTouchListener(new View.OnTouchListener() {
            //按住和松开的标识

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sign_Linear.setBackgroundResource(R.drawable.login_ed);
                register_Linear.setBackgroundResource(R.drawable.login_two_ed);
                return false;
            }
        });

        reginst_prassWord.setOnTouchListener(new View.OnTouchListener() {
            //按住和松开的标识

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                sign_Linear.setBackgroundResource(R.drawable.login_two_ed);
                register_Linear.setBackgroundResource(R.drawable.login_ed);
                return false;
            }
        });

        register_Forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterAct.this,ForgetAct.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {

        reginst_userName = (EditText) findViewById(R.id.reginst_userName);
        reginst_prassWord = (EditText) findViewById(R.id.reginst_prassWord);
        register_Forget = (TextView) findViewById(R.id.register_Forget);
        register_Register = (Button) findViewById(R.id.register_Register);
        Register_Login = (Button) findViewById(R.id.Register_Login);

        register_Register.setOnClickListener(this);
        Register_Login.setOnClickListener(this);
        register_Linear = (LinearLayout) findViewById(R.id.register_Linear);
        register_Linear.setOnClickListener(this);
        sign_Linear = (LinearLayout) findViewById(R.id.sign_Linear);
        sign_Linear.setOnClickListener(this);
        linear_Register = (LinearLayout) findViewById(R.id.linear_Register);
        linear_Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_Register:

                setPopupWindow();

                Presenter presenter = new Presenter(this,null,null);
                presenter.getUserOrWrod(reginst_userName.getText().toString(), reginst_prassWord.getText().toString());
                break;
            case R.id.Register_Login:

                Intent intent=new Intent(RegisterAct.this,LoginAct.class);
                startActivity(intent);

                break;
        }
    }

    private void setPopupWindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.registerpopupwindow, null);
        popupWindow = new PopupWindow(inflate,500,150);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(linear_Register, Gravity.CENTER,0,0);
    }


    @Override
    public void REGISTERSTAGE(String registerstarge) {

        //popupWindow.dismiss();
        if (registerstarge.equals("OK")) {
            Toast.makeText(RegisterAct.this, "登录成功", Toast.LENGTH_SHORT).show();
        } else if (registerstarge.equals("userOK")) {
            Toast.makeText(RegisterAct.this, "用户名不存在", Toast.LENGTH_SHORT).show();
        } else if (registerstarge.equals("请输入用户名或邮箱！")) {
            Toast.makeText(RegisterAct.this, "请输入用户名或邮箱...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterAct.this, "密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
