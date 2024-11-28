package org.example.DataObjects;

import lombok.Data;

@Data
public class Account {
    private String email;
    private String password;
    private String confirmPassword;
    private String pid;

    public Account(String email, String password, String confirmPassword, String pid) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.pid = pid;
    }
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
