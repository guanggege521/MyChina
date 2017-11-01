package com.example.my_china.greendaobase;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 吴肖光 on 2017/10/26.
 */
@Entity
public class ChinaUser {
    @Id(autoincrement = true)
    Long id;
    //用户名
    String username;
    //密码
    String password;
    //头像
    String hand;
    //加入时间
    String time;
    //地区
    String region;
    //所在平台
    String platform;
    //开发平台
    String developplatform;
    //擅长领域
    String goodfield;
    //签名
    String autograph;
    //手机号
    String cellphone;
    //性别
    String sex;

    public String getAutograph() {
        return this.autograph;
    }
    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }
    public String getGoodfield() {
        return this.goodfield;
    }
    public void setGoodfield(String goodfield) {
        this.goodfield = goodfield;
    }
    public String getPlatform() {
        return this.platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public String getRegion() {
        return this.region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getHand() {
        return this.hand;
    }
    public void setHand(String hand) {
        this.hand = hand;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCellphone() {
        return this.cellphone;
    }
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    public String getDevelopplatform() {
        return this.developplatform;
    }
    public void setDevelopplatform(String developplatform) {
        this.developplatform = developplatform;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Generated(hash = 809145744)
    public ChinaUser(Long id, String username, String password, String hand,
            String time, String region, String platform, String developplatform,
            String goodfield, String autograph, String cellphone, String sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hand = hand;
        this.time = time;
        this.region = region;
        this.platform = platform;
        this.developplatform = developplatform;
        this.goodfield = goodfield;
        this.autograph = autograph;
        this.cellphone = cellphone;
        this.sex = sex;
    }
    @Generated(hash = 971612634)
    public ChinaUser() {
    }

}
