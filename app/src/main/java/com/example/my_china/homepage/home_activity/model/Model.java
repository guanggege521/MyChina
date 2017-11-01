package com.example.my_china.homepage.home_activity.model;

import android.os.Handler;

import com.example.my_china.homepage.home_activity.Utils;

/**
 * Created by 吴肖光 on 2017/11/1.
 */
public class Model implements ModelInf {

    @Override
    public void getUrl(String url, Handler handler) {
        new Utils().getOkHttp(url,handler);
    }
}
