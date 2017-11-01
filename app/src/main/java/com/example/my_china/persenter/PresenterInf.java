package com.example.my_china.persenter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by 吴肖光 on 2017/10/26.
 */
public interface PresenterInf {
    void getUserOrWrod(String username, String prassword);
    void getUserOrWrodToUpDate(String username, String prassword);
    void getUserOrWrod(String username);
    void getUserOrWrod(String username, String prassword,boolean sex,String name);
}
