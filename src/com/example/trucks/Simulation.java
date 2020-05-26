package com.example.trucks;

import java.util.*;

public class Simulation {
    public static void main(String[] args) {
        //setting up
        String helpText = "Write:\n 1 to define new truck \n 2 to check status\n 3 to add step\n 4 to check the est. time of waiting\n 5 to wipe the data\n ? for help\n q to exit";
        System.out.println(helpText);
        Model model = new Model();

        //simulation starts
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        while (!command.equals("q")){
            switch (command) {
                case "1":
                    System.out.println("Write the weight of the truck:");
                    int weight;
                    try{
                        weight = Integer.parseInt(scanner.next());
                        boolean result = model.addTruck(weight);
                        if (result) {
                            System.out.println("Truck added! \n" + model.showState());
                        } else {
                            System.out.println("All queues are full");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("That's not a proper number");
                    }
                    break;
                case "2": {
                    String state = model.showState();
                    System.out.println(state);
                    break;
                }
                case "3":
                    model.nextStep();
                    System.out.println("Time is " + model.time);
                    break;
                case "4": {
                    System.out.println("Check by ID how long the truck has to wait:");
                    try{
                        int truckID = Integer.parseInt(scanner.next());
                        int timeLeft = model.searchTruck(truckID);
                        if (timeLeft==-1){
                            System.out.println("No truck with this ID");
                        } else if (timeLeft==0) {
                            System.out.println("Truck is being checked right now");
                        } else {
                            System.out.println("Truck has to wait " + timeLeft);
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("That's not a proper ID");
                    }
                    break;
                }
                case "5":
                    System.out.println("Are you sure you want to wipe the data? y/n");
                    String choice = scanner.next();
                    if (choice.equals("y")) {
                        System.out.println("Data wiped!");
                        model.wipeData();
                    } else {
                        System.out.println("Nothing changed!");
                    }
                    break;
                case "?":
                case "h":
                    System.out.println(helpText);
                    break;
            }
            command = scanner.next();
        }
    }
}