package multithreading_and_concurrency;

// Let's say you're building a ride-matching system like uber.
// Every time a ride request comes in, you spin up a new thread to match the rider to a driver.

// Issues while Creating Thread Manually in Real-World Systems:-

// Thread Explosion: Creating a new thread for each task can quickly lead to an excessive number of threads,
// overwhelming the system and degrading performance.
// Memory Issue: Each thread consumes system memory for its stack. Creating too many threads can lead to memory exhaustion,
// causing crashes or slowdowns.
// Thread Leaks: Failing to properly terminate threads results in thread leaks, where unused threads continue consuming resources,
// leading to performance degradation.
// Context Switching Overhead: Managing too many threads increases context switching, where the system saves and loads thread states.
// This overhead reduces overall system efficiency as the CPU spends more time switching between threads than doing real computational work.

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Ride Matching Service Class
class RideMatchingService{
    // Method handling ride request
    public void requestRide(String riderId){
        // Creating a new thread for the ride
        Thread matchThread = new Thread(() -> {
            System.out.println("Matching rider " + riderId + " to a driver...");
            // Simulate some processing
            try{
                Thread.sleep(1000); // Simulate a 1-second matching process
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Ride matched for rider " + riderId);
        });
        matchThread.start();
    }
}

// Executor Framework
// Using the newFixedThreadPool to manage threads

class EmailService{
    // Thread pool with 10 threads
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    // Method to send email
    public static void sendEmail(String recipient){
        EXECUTOR_SERVICE.execute(() -> {
            System.out.println("Sending email to " + recipient + " on " + Thread.currentThread().getName());
            try{
                // Simulate dummy work (sending an email)
                Thread.sleep(1000); // Simulate delay;
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            System.out.println("Email sent to " + recipient);
        });
    }
    // Main method to simulate sending multiple emails
    public static void emailServiceMain(){
        for(int i = 1; i <= 25; i++){
            sendEmail("User" + i + "@gmail.com"); // Send email to 25 users
        }
        EXECUTOR_SERVICE.shutdown(); // Gracefully shut down the executor
    }
}

// Methods to Submit Tasks:
// 1: execute() Method:
// Purpose: It is used to submit Runnable tasks (tasks that do not return any result).
// Return Type: It does not return a result. This method simply submits the task for execution and doesn't provide a way to track the result or exceptions.
// Usage: It's ideal for tasks where you don't need a result back from the task (e.g., logging, sending an email, etc.).
// 2: submit() Method:
// Purpose: It is used to submit both Runnable and Callable tasks (tasks that can return a result).
// Return Type: It returns a Future object, which allows you to track the result and handle exceptions thrown during the task's execution.
// Usage: It’s ideal for tasks where you need to capture and process the result (e.g., performing calculations or retrieving data).

// When to Use Each:
// execute() is best used for fire-and-forget tasks where you don't need a result back and don't need to track task completion.
// submit() is used when you need to retrieve a result or track progress with the help of a Future object.

// Future and Submit example
class FutureExample{
    public static void futureExampleMain() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(1000);
            return 31;
        });
        System.out.println("Doing other work...");
        Integer result = future.get(); // blocks until result is ready
        System.out.println("Result: " + result);
        executorService.shutdown();
    }
}

public class ThreadPoolsAndExecutors {
    static void main() throws ExecutionException, InterruptedException {
//        RideMatchingService rideService1 = new RideMatchingService();
//        RideMatchingService rideService2 = new RideMatchingService();
//        rideService1.requestRide("Raj");
//        System.out.println("task1 running...");
//        rideService2.requestRide("John Doe");
//        System.out.println("task2 running...");

//        EmailService.emailServiceMain();
        FutureExample.futureExampleMain();
    }
}
