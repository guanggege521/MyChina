package com.example.my_china.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.my_china.R;

public class Submitting extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_submitting;
    private EditText submitting_userName;
    private LinearLayout submitting_Linear;
    private EditText submitting_prassWord;
    private LinearLayout submitting_Two_Linear;
    private Button submitting_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitting);
        initView();
        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitting_Register:

                break;
        }
    }

}
