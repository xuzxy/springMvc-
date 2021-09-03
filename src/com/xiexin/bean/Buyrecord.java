package com.xiexin.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Buyrecord {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private int id;
    private int fid;
    private String sname;
    private int num;
    private Date buydate;

    @Override
    public String toString() {
        return "Buyrecord{" +
                "id=" + id +
                ", fid=" + fid +
                ", sname='" + sname + '\'' +
                ", num=" + num +
                ", buydate=" + buydate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getBuydate() {
        return buydate;
    }

    public void setBuydate(Date buydate) {
        this.buydate = buydate;
    }
}
