package com.example.trucks;

public class Truck {
    public int truckID;
    public int weight;

    public Truck(int weight, int id) {
        this.weight = weight;
        this.truckID  = id;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getTruckID() {
        return this.truckID;
    }

    public void setWeight(int value) {
        this.weight = value;
    }
}