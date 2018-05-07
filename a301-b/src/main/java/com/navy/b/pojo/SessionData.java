package com.navy.b.pojo;

public class SessionData {
    private String name;
    private String cell;

    public SessionData(String name, String email) {
        this.name = name;
        this.cell = email;
    }

    public String getName() {
        return name;
    }
    public String getCell() {
        return cell;
    }
}
