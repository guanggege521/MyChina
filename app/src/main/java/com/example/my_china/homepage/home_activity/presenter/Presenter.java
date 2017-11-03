package com.example.my_china.homepage.home_activity.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.my_china.homepage.home_activity.bean.ZhengHe_DongTai;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_BoKe;
import com.example.my_china.homepage.home_activity.bean.ZhongHe_ZiXun;
import com.example.my_china.homepage.home_activity.homefragment.Zhonghe_Copy;
import com.example.my_china.homepage.home_activity.model.Model;
import com.example.my_china.homepage.home_activity.model.ModelInf;
import com.example.my_china.homepage.home_activity.view.ViewInf;

/**
 * Created by 吴肖光 on 2017/11/1.
 */
public class Presenter implements PresenterInt {

    ViewInf viewInf;
    ModelInf model;
    public Presenter(Zhonghe_Copy zhonghe_copy) {
        model=new Model();
        viewInf=zhonghe_copy;
    }

    @Override
    public void getUtl(String url) {
        model.getUrl(url,handler);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    ZhongHe_ZiXun zhongHe_ziXun = (ZhongHe_ZiXun) msg.obj;
                    viewInf.getMyBean(zhongHe_ziXun,null,null);
                    break;
                case 101:
                    ZhongHe_BoKe zhongHe_boKe = (ZhongHe_BoKe) msg.obj;
                    viewInf.getMyBean(null,zhongHe_boKe,null);
                    break;
                case 102:
                    ZhengHe_DongTai zhengHe_dongTai = (ZhengHe_DongTai) msg.obj;
                    viewInf.getMyBean(null,null,zhengHe_dongTai);
                    break;
            }
        }
    };
}
