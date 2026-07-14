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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public static void main(){
        for(int i = 1; i <= 25; i++){
            sendEmail("User" + i + "@gmail.com"); // Send email to 25 users
        }
        EXECUTOR_SERVICE.shutdown(); // Gracefully shut down the executor
    }
}

public class ThreadPoolsAndExecutors {
    static void main() {
//        RideMatchingService rideService1 = new RideMatchingService();
//        RideMatchingService rideService2 = new RideMatchingService();
//        rideService1.requestRide("Raj");
//        System.out.println("task1 running...");
//        rideService2.requestRide("John Doe");
//        System.out.println("task2 running...");
    }
}
