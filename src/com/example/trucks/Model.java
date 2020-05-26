package com.example.trucks;

public class Model {
    public int time;
    public int id=1;
    public int queueSize = 6;
    public Queue queue1;
    public Queue queue2;

    public Model() {
        this.queue1 = new Queue(queueSize);
        this.queue2 = new Queue(queueSize);
    }

    public boolean addTruck(int weight) {
        Truck truck = new Truck(weight, id);
        if (queue1.getCurrentWaitTime()>queue2.getCurrentWaitTime() && queue2.isQueueEmpty()){
            queue2.addTruck(truck);
        } else if (queue1.isQueueEmpty()){
            queue1.addTruck(truck);
        } else if (queue2.isQueueEmpty()){
            queue2.addTruck(truck);
        } else {
            return false;
        }
        nextID();
        return true;
    }

    private void nextID() {
        id+=1;
    }

    public String showState(){
        return "QN | CargoCHK | 1        | 2        | 3        | 4        | 5        |\n"
                + "Q1 " + queue1.getQueue() + "\n" + "Q2 " +queue2.getQueue();
    }

    public void nextStep() {
        time+=1;
        queue1.cargoCheck();
        queue2.cargoCheck();
        checkOptimisation();
    }

    private void checkOptimisation() {
        float estimate = queue1.avgTime()+queue2.avgTime(); //average waiting time for queues before changing anything
        String state = showState();
        System.out.println(state);
        System.out.println("First estimate: "+estimate);
        //looking for the shorter queue
        if (queue1.getCurrLen()<=queue2.getCurrLen()){
            for (int i=2; i<queue1.getCurrLen(); i+=1){
                swapTrucks(i);
                float newEstimate = queue1.avgTime()+queue2.avgTime();
                System.out.println("New estimate: "+newEstimate);
                if (newEstimate>=estimate){
                    swapTrucks(i);
                } else {
                    estimate = newEstimate;
                }
            }
        } else if (queue2.getCurrLen()<queue1.getCurrLen()){
            for (int i=2; i<queue2.getCurrLen(); i+=1){
                swapTrucks(i);
                float newEstimate = queue1.avgTime()+queue2.avgTime();
                System.out.println("New estimate: "+newEstimate);
                if (newEstimate>=estimate){
                    swapTrucks(i);
                } else {
                    estimate = newEstimate;
                }
            }
        }
        String state2 = showState();
        System.out.println(state2);
    }

    private void swapTrucks(int position) {
        Truck truckQ1 = queue1.getTruckAtPos(position);
        Truck truckQ2 = queue2.getTruckAtPos(position);
        queue1.putTruck(truckQ2, position);
        queue2.putTruck(truckQ1, position);
    }

    public void wipeData() {
        time = 0;
        id = 1;
        this.queue1 = new Queue(queueSize);
        this.queue2 = new Queue(queueSize);
    }

    public int searchTruck(int truckID) {
        if (queue1.searchTruck(truckID)){
            return queue1.estTime(truckID);
        } else if (queue2.searchTruck(truckID)){
            return queue2.estTime(truckID);
        } else {
            return -1;
        }
    }
}