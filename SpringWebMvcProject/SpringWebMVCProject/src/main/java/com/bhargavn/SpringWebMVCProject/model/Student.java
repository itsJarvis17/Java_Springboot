package com.bhargavn.SpringWebMVCProject.model;

public class Student {
    private int sid;
    private String sname;
    private String dept;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return sname  +
                " From " + dept + " Dept";
    }
}
