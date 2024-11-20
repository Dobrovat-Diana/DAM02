package com.digital.marketing.be.service.dto;

import com.digital.marketing.be.repository.entity.UserRole;

public class LoginReq {

    private String userEmail;
    private String password;
    private UserRole userRole;

    public LoginReq(String userEmail, String password, UserRole userRole) {
        this.userEmail = userEmail;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
