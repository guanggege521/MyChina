package com.example.my_china.homepage.home_activity;

import android.os.Handler;

import com.example.my_china.homepage.home_activity.bean.ZhongHe_BoKe;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_ZiXun;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 吴肖光 on 2017/11/1.
 */
public class Utils {
    public  void getOkHttp(final String url, final Handler handler){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request build = new Request.Builder().url("http://www.oschina.net/" + url).build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                XStream xStream=new XStream();
                if(url.equals("action/api/news_list")){
                    xStream.alias("oschina", ZhongHe_ZiXun.class);
                    xStream.alias("news", ZhongHe_ZiXun.NewsBean.class);
                    xStream.alias("newstype", ZhongHe_ZiXun.NewsBean.NewstypeBean.class);
                    ZhongHe_ZiXun zhongHe_ziXun = (ZhongHe_ZiXun) xStream.fromXML(string);
                    handler.obtainMessage(100,zhongHe_ziXun).sendToTarget();
                    return;

                }else if(url.equals("action/api/blog_list")){
                    xStream.alias("oschina", ZhongHe_BoKe.class);
                    xStream.alias("blog", ZhongHe_BoKe.BlogBean.class);
                    ZhongHe_BoKe zhongHe_boKe = (ZhongHe_BoKe) xStream.fromXML(string);
                    handler.obtainMessage(101,zhongHe_boKe).sendToTarget();
                    return;
                }

            }
        });
    }
}
