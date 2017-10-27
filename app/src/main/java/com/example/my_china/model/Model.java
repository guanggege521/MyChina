package com.example.my_china.model;

import android.content.Context;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.alex.greendao.gen.ChinaUserDao;
import com.alex.greendao.gen.DaoSession;
import com.example.my_china.greendaobase.ChinaUser;
import com.example.my_china.serve.App;
import com.example.my_china.utils.RegisterUtils;

import java.util.List;

/**
 * Created by 吴肖光 on 2017/10/26.
 */
public class Model implements ModelInf {

    @Override
    public void INSERTUSERDAO(String username, String prassword, MyStage stage, Handler handler) {
        //查询数据库
        new RegisterUtils().loadRegisterUserDao(username,prassword,stage,handler);
    }

    @Override
    public void INSERTUSERDAO(String username, MyStage stage, Handler handler) {
        new RegisterUtils().loadRegisterUserDao(username,stage,handler);
    }
}
