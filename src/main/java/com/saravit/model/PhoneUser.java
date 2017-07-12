package com.saravit.model;

/**
 * Created by soengsaravit on 7/12/17.
 */
public class PhoneUser {

    private String user_id;
    private String phone_number;
    private String access_token;

    public PhoneUser() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
