package com.example.my_china.persenter;

import android.content.Context;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.my_china.activity.ForgetAct;
import com.example.my_china.activity.RegisterAct;
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

    public Presenter(RegisterAct registerAct, ForgetAct forgetAct) {


        if(registerAct!=null){
            viewInf=registerAct;
        }
        if(forgetAct!=null){
            viewInf=forgetAct;
        }
        modelInf=new Model();

    }

    @Override
    public void getUserOrWrod(String username, String prassword) {
        modelInf.INSERTUSERDAO(username,prassword,this,handler);
    }

    @Override
    public void getUserOrWrod(String username) {
        modelInf.INSERTUSERDAO(username,this,handler);
    }

    @Override
    public void USERSTAGE(String registertage) {
        viewInf.REGISTERSTAGE(registertage);
    }

    Handler handler=new Handler();
}
