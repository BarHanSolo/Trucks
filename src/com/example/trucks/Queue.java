package com.example.trucks;

import java.util.ArrayList;

public class Queue {
    int queueSize;
    ArrayList<Truck> content = new ArrayList<>();

    public Queue(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getCurrentWaitingTime() {
        int length = 0;
        for (Truck truck:content
        ) {
            length+=truck.getWeight();
        }
        return length;
    }

    public void addTruck(Truck truck) {
        content.add(truck);
    }

    public String toString(){
        StringBuilder queue = new StringBuilder();
        for (Truck truck:content
        ) {
            queue.append("| ");
            queue.append("ID:").append(truck.getTruckID()).append(" ");
            queue.append("W:").append(truck.getWeight()).append(" ");
        }
        return queue.toString();
    }
    public boolean isQueueNotFull(){
        return content.size() < queueSize;
    }

    public void cargoCheck() {
        if (content.size()!=0){
            Truck firstTruck = content.get(0);
            if (firstTruck.getWeight()==1){
                content.remove(0);
            } else {
                int newWeight = content.get(0).getWeight()-1;
                firstTruck.setWeight(newWeight);
            }
        }
    }

    public boolean searchTruck(int truckID) {
        for (Truck truck:content) {
            if (truck.getTruckID() == truckID){
                return true;
            }
        }
        return false;
    }

    public int estTime(int truckID) {
        int time=0;
        for (Truck truck:content
        ) {
            if (truck.getTruckID() == truckID){
                return time;
            }
            time+=truck.getWeight();
        }
        return time;
    }

    public float avgTime() {
        float countTrucksInQueue = content.size();
        //no trucks or truck only being checked
        if (content.size()<2){
            return 0;
        }
        int waitTime = 0;
        int counterMultiplier = 1;
        for (int i=content.size(); i>0; i-=1){
            waitTime+=content.get(i-1).getWeight()*counterMultiplier;
            counterMultiplier+=1;
        }
        return waitTime/(countTrucksInQueue);
    }

    public int getCurrLen() {
        return content.size();
    }

    public Truck getTruckAtPos(int position) {
        return content.get(position);
    }

    public void putTruck(Truck truck, int position) {
        content.set(position, truck);
    }
}