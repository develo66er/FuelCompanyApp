package com.models;

public class Fuel {
    private long id;
    private String name;
    private String type;
    private Double price;
    private long petrolstationId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPetrolstationId() {
        return petrolstationId;
    }

    public void setPetrolstationId(long petrolstationId) {
        this.petrolstationId = petrolstationId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
}
