package com.models;

public class Dispenser {
    private long id;
    private String type;
    private int count;
    private String model;
    private long petrolstationId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getPetrolstationId() {
        return petrolstationId;
    }

    public void setPetrolstationId(long petrolstationId) {
        this.petrolstationId = petrolstationId;
    }
    
}
