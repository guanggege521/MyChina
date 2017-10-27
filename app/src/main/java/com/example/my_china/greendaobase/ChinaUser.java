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
    String username;
    String password;
    String hand;
    String time;
    String region;
    String platform;
    String goodfield;
    String autograph;
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
    @Generated(hash = 1382985789)
    public ChinaUser(Long id, String username, String password, String hand,
            String time, String region, String platform, String goodfield,
            String autograph) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hand = hand;
        this.time = time;
        this.region = region;
        this.platform = platform;
        this.goodfield = goodfield;
        this.autograph = autograph;
    }
    @Generated(hash = 971612634)
    public ChinaUser() {
    }

}
