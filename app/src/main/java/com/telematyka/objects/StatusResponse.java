package com.telematyka.objects;

/**
 * Created by root on 25.05.17.
 */

public class StatusResponse {
    private String status;
    private String login;

    public String getResponse(){
        return this.status;
    }
    public void setResponse(String password){
        this.status = password;
    }
    public String getLogin(){
        return this.login;
    }
    public void setLogin(String login){
        this.login = login;
    }
}
