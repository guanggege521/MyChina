package com.example.my_china.persenter;

import android.content.Context;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.my_china.activity.ForgetAct;
import com.example.my_china.activity.LoginAct;
import com.example.my_china.activity.RegisterAct;
import com.example.my_china.activity.ReplacementAct;
import com.example.my_china.activity.Submitting;
import com.example.my_china.model.Model;
import com.example.my_china.model.ModelInf;
import com.example.my_china.model.MyStage;
import com.example.my_china.view.ViewInf;

/**
 * Created by 吴肖光 on 2017/10/26.
 */
public class Presenter implements PresenterInf,MyStage {


    ViewInf viewInf;
    ModelInf modelInf;

    public Presenter(RegisterAct registerAct, ForgetAct forgetAct, LoginAct loginAct, Submitting submitting, ReplacementAct replacementAct) {


        if(registerAct!=null){
            viewInf=registerAct;
        }
        if(forgetAct!=null){
            viewInf=forgetAct;
        }
        if(loginAct!=null){
            viewInf=loginAct;
        }
        if(submitting!=null){
            viewInf=submitting;
        }
        if(replacementAct!=null){
            viewInf=replacementAct;
        }

        modelInf=new Model();

    }





    //登录
    @Override
    public void getUserOrWrod(String username, String prassword) {
        modelInf.INSERTUSERDAO(username,prassword,this,handler);
    }

    //重置密码
    @Override
    public void getUserOrWrodToUpDate(String username, String prassword) {
        modelInf.REPLACEMENT(username,prassword,this,handler);
    }

    //找回
    @Override
    public void getUserOrWrod(String username) {
        modelInf.INSERTUSERDAO(username,this,handler);
    }

    //注册
    @Override
    public void getUserOrWrod(String username, String prassword, boolean sex, String name) {
        modelInf.INSERTUSERDAO(username,prassword,sex,name,handler,this);
    }

    //view的实现
    @Override
    public void USERSTAGE(String registertage) {
        viewInf.REGISTERSTAGE(registertage);
    }

    Handler handler=new Handler();
}
