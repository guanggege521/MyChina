package com.example.my_china.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.my_china.R;
import com.example.my_china.persenter.Presenter;
import com.example.my_china.view.ViewInf;

public class ReplacementAct extends AppCompatActivity implements View.OnClickListener,ViewInf {

    private ImageView back_replace;
    private EditText replace_prassWord;
    private LinearLayout replace_Two_Linear;
    private Button replace_Register;
    private String call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement);
        initView();
        initOnCLikc();

        Intent intent = getIntent();
        call = intent.getStringExtra("call");

    }

    private void initOnCLikc() {
        back_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        back_replace = (ImageView) findViewById(R.id.back_replace);
        replace_prassWord = (EditText) findViewById(R.id.replace_prassWord);
        replace_Two_Linear = (LinearLayout) findViewById(R.id.replace_Two_Linear);
        replace_Register = (Button) findViewById(R.id.replace_Register);

        replace_prassWord.setOnClickListener(this);
        replace_Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.replace_Register:

                Presenter presenter=new Presenter(null,null,null,null,this);
                presenter.getUserOrWrodToUpDate(call,replace_prassWord.getText().toString());

                break;
        }
    }

    @Override
    public void REGISTERSTAGE(String registerstarge) {
        //判断信息
        Toast.makeText(ReplacementAct.this, registerstarge, Toast.LENGTH_SHORT).show();
//        if(registerstarge.equals("密码重置成功！")){
//            Intent intent=new Intent(this, RegisterAct.class);
//            startActivity(intent);
//            finish();
//        }

        switch (registerstarge){
            case "密码重置成功！":
                Intent intent=new Intent(this, RegisterAct.class);
                startActivity(intent);
                finish();
            break;
        }
    }
}
