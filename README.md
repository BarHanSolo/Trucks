# Trucks
Application is made to organize queues of trucks during customs. As it is presented in the picture trucks are arriving to customs where the documents are being checked. Then they are being directed to one of two queues. Each queue has 5 truck spaces. When the truck finishes waiting it drives to the cargo check. That takes time proportional to the weight of the cargo. The main problem that this application is solving is optimizing the queue.

![Picture 1](/fig1.PNG)

The application is running in the console and takes input from the keyboard. There are 7 commands in total.

## Command "1"
By writing "1" and hitting enter user can add new truck to the queue. After that system requires to set the weight of the truck. Newly created truck has automatically set ID number and it is assigned to the shorter (in time) queue. If shorter queue is full, than it is assigned to the longer one. If both queues are full the truck is not assigned and user is informed about that.

## Command "2"
Command "2" allows the user to check the status of the system. It shows the queues with all trucks. In the picture there are two queues. Trucks with ID:1 and ID:2 are being checked in customs ("Towar 1" and "Towar 2" on first picture). Trucks 3, 4, 6, 7 stays in the first queue. Trucks 5 and 8 in the second one.

![Picture 2](/fig2.PNG)

## Command "3"
Command allows user to move the time one step ahead. Each execution of this function changes the time variable. It changes the value of the trucks that are being checked. If the value gets to 0, the truck is gone and the queue is moved. Each time this command is executed a function checkOptimisation() runs through all the trucks on places from 2 to 5 in queues. This function counts sum of estimated time that trucks need to wait. Than it tries to swap trucks between queues to check if it shortens the extimated time. That happens separatelly with all desired spaces.

## Command "4"
This command allows user to check how long a truck has to wait from current moment. As input this command takes ID of the truck and returns estimated time for the truck. If the truck is being checked right now it informs the user about that. If truck of the chosen ID is not in any queue the user gets information about that.

## Command "5"
Command "5" lets user to wipe all the data about trucks from the system. It asks the user to confirm the data wipe to prevent from accidental data losses.

## Command "?" or "h"
Shows set of all instructions with short descriptions (the same one is being showed when starting the application).

## Command "q"
Breaks the loop and terminates the application.
