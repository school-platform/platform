package com.example.demo.domain;

import java.util.Date;

public class Actinfos {
    private Integer actId;

    private Date endtime;

    private Integer max;

    private Byte tmax;

    private Byte tmin;

    private String message;

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Byte getTmax() {
        return tmax;
    }

    public void setTmax(Byte tmax) {
        this.tmax = tmax;
    }

    public Byte getTmin() {
        return tmin;
    }

    public void setTmin(Byte tmin) {
        this.tmin = tmin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}