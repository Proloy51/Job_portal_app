package com.example.jobportalapp;

public class Apply {
    private  String user;
 private String id;
 private String  title;
 private String candid;
 private String com;
 private String hsc;
 private String grad;
 private String postgrad;
 private String exp;
 private String spec;
 private String skill;
 private String status;

    public Apply() {
    }

    public Apply(String user,String id, String title, String candid, String com, String hsc, String grad, String postgrad, String exp, String spec, String skill,String status) {
        this.user = user;
        this.id = id;
        this.title = title;
        this.candid = candid;
        this.com = com;
        this.hsc = hsc;
        this.grad = grad;
        this.postgrad = postgrad;
        this.exp = exp;
        this.spec = spec;
        this.skill = skill;
        this.status = status;
    }

    public String getUser(){return user;}

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCandid() {
        return candid;
    }

    public String getCom() {
        return com;
    }

    public String getHsc() {
        return hsc;
    }

    public String getGrad() {
        return grad;
    }

    public String getPostgrad() {
        return postgrad;
    }

    public String getExp() {
        return exp;
    }

    public String getSpec() {
        return spec;
    }

    public String getSkill() {
        return skill;
    }

    public String getStatus(){return status;}

    public void setUser(String user){this.user = user;}

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCandid(String candid) {
        this.candid = candid;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public void setHsc(String hsc) {
        this.hsc = hsc;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void setPostgrad(String postgrad) {
        this.postgrad = postgrad;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setStatus(String status){this.status = status;}
}
