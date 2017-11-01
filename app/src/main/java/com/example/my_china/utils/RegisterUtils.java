package com.example.my_china.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.alex.greendao.gen.ChinaUserDao;
import com.alex.greendao.gen.DaoSession;
import com.example.my_china.R;
import com.example.my_china.greendaobase.ChinaUser;
import com.example.my_china.model.MyStage;
import com.example.my_china.serve.App;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by 吴肖光 on 2017/10/26.
 */
public class RegisterUtils {

    //登录
    public void loadRegisterUserDao( final String username, final String prassword, final MyStage stage, Handler handler){


        //判断用户名或密码是否为空
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(prassword)){


            //查询数据库
            Runnable runnable=new Runnable() {
                @Override
                public void run() {

                    List<ChinaUser> chinaUsers = getloadChinaUsers();
                    //tagers 判断结果
                    String tagers="";
                    //登录判断 用户名密码是否一致
                    for (int i = 0; i < chinaUsers.size(); i++) {
                        if(chinaUsers.get(i).getCellphone().toString().equals(username) ){
                            tagers="userOk";
                        }
                    }

                    if("userOk".equals(tagers)){
                        for (int i = 0; i < chinaUsers.size(); i++) {
                            if(chinaUsers.get(i).getPassword().toString().equals(prassword) ){
                                stage.USERSTAGE("OK");
                                return;
                            }
                        }
                        stage.USERSTAGE("NO");
                        return;

                    }else{
                        stage.USERSTAGE("userOK");
                        return;
                    }
                }
            };
            handler.post(runnable);

        }else{
            stage.USERSTAGE("请输入用户名或邮箱！");
            return;
        }
    }

    //找回
    public void loadRegisterUserDao( final String username, final MyStage stage, Handler handler){

        if(!TextUtils.isEmpty(username)){
            if(isPhoneLegal(username)){
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        List<ChinaUser> chinaUsers = getloadChinaUsers();

                        String tagers="";
                        //登录判断 用户名密码是否一致
                        for (int i = 0; i < chinaUsers.size(); i++) {
                            if(chinaUsers.get(i).getCellphone().toString().equals(username) ){
                                tagers="用户名已被注册";
                                stage.USERSTAGE("用户名已被注册");
                                break;
                            }
                        }

                        if(!"用户名已被注册".equals(tagers)){
                            tagers="用户名有误";
                            stage.USERSTAGE("用户名有误");
                            return;
                        }
                    }
                };
                handler.post(runnable);
            }else{
                stage.USERSTAGE("请输入正确的手机号");
                return;
            }
        }else{
            stage.USERSTAGE("手机号不能为空");
            return;
        }
    }

    //注册
    public void loadRegisterUserDao(final String username, final String prassword, final boolean sex, final String name, Handler handler, final MyStage stage){
        //获取数据库
        if(name.length()<6||name.length()>18){
            stage.USERSTAGE("用户名长度应为6~18位");
            return;
        }else{
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    App app = App.getApp();
                    DaoSession sessionDao = app.getSessionDao();
                    ChinaUserDao chinaUserDao = sessionDao.getChinaUserDao();

                    ChinaUser chinaUser = new ChinaUser();
                    chinaUser.setUsername(name);
                    chinaUser.setPassword(prassword);
                    chinaUser.setCellphone(username);
                    if(sex){
                        chinaUser.setSex("男");
                    }else{
                        chinaUser.setSex("女");
                    }

                    chinaUserDao.insert(chinaUser);
                    stage.USERSTAGE("注册成功");
                    return;
                }
            };

            handler.post(runnable);
        }
    }

    private List<ChinaUser> getloadChinaUsers() {
        App app = App.getApp();
        DaoSession sessionDao = app.getSessionDao();
        ChinaUserDao chinaUserDao = sessionDao.getChinaUserDao();
        return chinaUserDao.loadAll();
    }

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //重置密码
    public void loadRegisterReplacement(final String call, final String prassword, final MyStage stage, final Handler handler) {
        if(prassword.length()<6||prassword.length()>18){
            stage.USERSTAGE("用户名长度应为6~18位");
            return;
        }else{
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    //获取数据库
                    App app = App.getApp();
                    DaoSession sessionDao = app.getSessionDao();
                    ChinaUserDao chinaUserDao = sessionDao.getChinaUserDao();

                    //通过手机号进行查询
                    ChinaUser unique = chinaUserDao.queryBuilder().where((ChinaUserDao.Properties.Cellphone.eq(call))).unique();
                    unique.setPassword(prassword);

                    //重置密码
                    ChinaUserDao chinaUserDao2 = sessionDao.getChinaUserDao();
                    chinaUserDao2.update(unique);
                    stage.USERSTAGE("密码重置成功！");
                    return;
                }
            };

            handler.post(runnable);
        }
    }
}
