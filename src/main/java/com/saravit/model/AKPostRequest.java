package com.saravit.model;

/**
 * Created by soengsaravit on 7/12/17.
 */
public class AKPostRequest {
    private String csrf;
    private String code;

    public AKPostRequest(){}

    public AKPostRequest(String csrf,String code){
        this.csrf = csrf;
        this.code = code;
    }

    public String getCsrf() {
        return csrf;
    }

    public void setCsrf(String csrf) {
        this.csrf = csrf;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
