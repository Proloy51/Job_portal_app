package com.example.jobportalapp;

public class Accepted {
    String id;
    String title;
    String com;
    String stat;

    public Accepted() {
    }

    public Accepted(String id, String title, String com, String stat) {
        this.id = id;
        this.title = title;
        this.com = com;
        this.stat = stat;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCom() {
        return com;
    }

    public String getStat() {
        return stat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
