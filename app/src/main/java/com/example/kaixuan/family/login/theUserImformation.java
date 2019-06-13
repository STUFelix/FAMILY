package com.example.kaixuan.family.login;

import cn.bmob.v3.BmobUser;

public class theUserImformation extends BmobUser {
    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
