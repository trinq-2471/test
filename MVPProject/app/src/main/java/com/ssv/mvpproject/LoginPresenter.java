package com.ssv.mvpproject;

public class LoginPresenter {

    private LoginInterface loginInterface;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }

    public void Login(User user ){
        if (user.IsEmail() && user.IsPassWord()){
            loginInterface.loginSuccess();
        }else {
            loginInterface.loginError();
        }
    }
}
