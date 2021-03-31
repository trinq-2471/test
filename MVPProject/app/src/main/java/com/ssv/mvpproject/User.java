package com.ssv.mvpproject;

import android.text.TextUtils;
import android.util.Patterns;

public class User {
    private String email;
    private String passWord;

    public User(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Boolean IsEmail(){
        boolean result = true;
        if ( TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
            result = false;
        }
        return result;
    }

    public Boolean IsPassWord(){
        boolean result = true;
        if ( TextUtils.isEmpty(passWord) || passWord.length() < 7 ){
            result = false;
        }
        return result;
    }
}
