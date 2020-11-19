package com.ss.domain;

import java.util.Date;

public class AccessToken {
    private String errcode;
    private String errmsg;
    private String token;
    private int expiresIn;
    private Date accessTokenTime;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getAccessTokenTime() {
        return accessTokenTime;
    }

    public void setAccessTokenTime(Date accessTokenTime) {
        this.accessTokenTime = accessTokenTime;
    }
}
