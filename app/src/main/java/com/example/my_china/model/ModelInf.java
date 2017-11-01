package com.example.my_china.model;

import android.content.Context;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by 吴肖光 on 2017/10/26.
 */
public interface ModelInf {

    //重置 replacement
    void REPLACEMENT( String call, String prassword, MyStage stage, Handler handler);


    //登录
    void INSERTUSERDAO( String username, String prassword, MyStage stage, Handler handler);

    //找回
    void INSERTUSERDAO( String username, MyStage stage, Handler handler);

    //注册
    void INSERTUSERDAO( String username, String prassword, boolean sex, String name,Handler handler, MyStage stage);

}
