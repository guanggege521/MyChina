package com.example.my_china.model;

import android.content.Context;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by 吴肖光 on 2017/10/26.
 */
public interface ModelInf {
    void INSERTUSERDAO( String username, String prassword, MyStage stage, Handler handler);

    void INSERTUSERDAO( String username, MyStage stage, Handler handler);
}
