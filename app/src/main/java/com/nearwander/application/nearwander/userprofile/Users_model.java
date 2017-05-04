package com.nearwander.application.nearwander.userprofile;

/**
 * Created by Rafli on 5/3/17.
 */

public class Users_model {
    private String email,id_user,name,phone_number,sex,status;

    public Users_model() {
    }

    public Users_model(String email, String id_user, String name, String phone_number, String sex, String status) {
        this.email = email;
        this.id_user = id_user;
        this.name = name;
        this.phone_number = phone_number;
        this.sex = sex;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
