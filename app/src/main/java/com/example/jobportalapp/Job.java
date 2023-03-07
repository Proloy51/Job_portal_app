package com.example.jobportalapp;

public class Job {
    private String id;
    private String title;
    private String com;
    private String sal;
    private String desc;
    private String req;
    private String date;

    public Job() {
    }

    public Job(String id, String title, String com, String sal, String desc, String req, String date) {
        this.id = id;
        this.title = title;
        this.com = com;
        this.sal = sal;
        this.desc = desc;
        this.req = req;
        this.date = date;
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

    public String getSal() {
        return sal;
    }

    public String getDesc() {
        return desc;
    }

    public String getReq() {
        return req;
    }

    public String getDate() {
        return date;
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

    public void setSal(String sal) {
        this.sal = sal;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
