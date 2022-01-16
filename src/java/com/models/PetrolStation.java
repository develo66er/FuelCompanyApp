package com.models;

import java.sql.Time;

public class PetrolStation {

    private long id;
    private String address;
    private Time startTime;;
    private Time finishTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        if (startTime != null) {
            this.startTime = Time.valueOf(startTime);
        }
    }

    public Time getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        if (finishTime != null) {
            this.finishTime = Time.valueOf(finishTime);
        }
    }

}
