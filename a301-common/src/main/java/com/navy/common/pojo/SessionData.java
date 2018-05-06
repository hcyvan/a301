package com.navy.common.pojo;

public class SessionData {
    private String name;
    private String email;

    public SessionData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
