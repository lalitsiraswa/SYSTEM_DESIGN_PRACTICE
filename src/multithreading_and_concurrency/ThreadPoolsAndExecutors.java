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

import java.util.concurrent.*;

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

// Shutdown Methods in Executor Class:
// 1. shutdown() method:
// Purpose: Initiates an orderly shutdown of the executor service. Once this method is called, the executor will stop accepting new tasks but will continue to process the tasks that have already been submitted.
// Return Type: void (No return value)
// Usage:
// shutdown() is typically used when you want to allow the executor to finish executing all tasks in the queue before shutting down.
// It ensures that the executor doesn't accept any new tasks but still processes the tasks that have already been submitted.
// 2. shutdownNow() method:
// Purpose: Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were waiting to be executed.
// Return Type: List<Runnable> — Returns a list of the tasks that were in the executor’s queue but were not executed.
// Usage:
// shutdownNow() tries to immediately stop all tasks, including those that are currently running.
// It does not guarantee that all tasks will be stopped; it may just attempt to interrupt them.
// It returns a list of tasks that have not yet started executing, so you can handle those tasks or retry them later if needed.

// Fixed vs Cached vs Scheduled Thread Pools
// 1. Fixed Thread Pool:
// A Fixed Thread Pool creates a pool with a fixed number of threads. Once a task is submitted, the executor assigns it to an
// available thread from the pool. If all threads are busy, new tasks are queued until a thread becomes available.
// Use Case:
// Applications where the number of tasks is known in advance, and the system should process a fixed number of
// concurrent tasks (e.g., handling a fixed number of user requests simultaneously).
// Advantages:
// Predictable resource usage: The number of threads is fixed, so system resources are predictable.
// Better control: You can control the maximum number of concurrent threads, avoiding system overload.
// Disadvantages:
// Limited scalability: If all threads are busy and the task queue fills up, tasks must wait in the queue, potentially delaying execution.
// Underutilization: If fewer tasks are available than the number of threads, some threads may remain idle, wasting system resources.
// 2. Cached Thread Pool:
// A Cached Thread Pool creates new threads as needed but reuses previously constructed threads when they are available.
// If a thread remains idle for more than 60 seconds, it is terminated and removed from the pool.
// Use Case:
// Short-lived tasks that are executed intermittently, such as handling burst traffic or processing small background
// tasks where thread usage is unpredictable.
// Advantages:
// Scalable: Threads are created dynamically, and the pool can grow as needed to handle bursts of tasks.
// Efficient resource use: Threads are reused whenever possible, reducing the cost of thread creation.
// Disadvantages:
// Potential for thread explosion: If the system experiences a high volume of tasks at once, a large number of threads may
// be created, leading to resource exhaustion.
// Less predictable resource usage: The number of threads can fluctuate significantly, making resource management more difficult.
// 3. Scheduled Thread Pools
// A Scheduled Thread Pool allows you to schedule tasks with fixed-rate or fixed-delay execution policies. It supports delayed
// or periodic execution of tasks, making it useful for scheduling tasks at regular intervals or after a specific delay.

class SessionCleaner {
    public static void sessionCleanerMain(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            System.out.println("Cleaning up expired sessions...");
        };
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}

// Explanation:
// In the above example, a session-cleaning task is scheduled to run every 5 seconds, starting immediately (initialDelay = 0).
// This is achieved using scheduleAtFixedRate(), which ensures periodic execution using a ScheduledExecutorService.
// Use Case:
// Applications that need to perform tasks at fixed intervals or after a certain delay, such as periodic data synchronization,
// background maintenance tasks, or scheduled jobs.
// Advantages:
// Task scheduling: Allows scheduling tasks with a delay or periodic execution.
// Flexible: Provides methods for scheduling tasks at fixed-rate intervals or with a fixed delay between executions.
// Disadvantages:
// Less efficient for short-lived tasks: Not ideal for tasks that are short-lived or don’t need to be scheduled periodically.
// Thread management overhead: Managing scheduled tasks requires additional overhead for tracking execution times and intervals.

// Below code is not thread safe.
// You will get inconsistent results
class ExecutorServiceExample{
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private int count = 0;
    public void executorServiceExampleMain() throws ExecutionException, InterruptedException {
        Runnable task = () ->{
            System.out.println(Thread.currentThread().getName());
            for(int i = 1; i <= 10000; i++){
                count++;
            }
        };
        Future<?> f1 = executorService.submit(task);
        Future<?> f2 = executorService.submit(task);
        // get() waits until the task finishes.
        f1.get();
        f2.get();
        System.out.println("COUNT: " + count);
        count = 0;
        f1 = executorService.submit(task);
        f2 = executorService.submit(task);
        f1.get();
        f2.get();
        System.out.println("COUNT: " + count);
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
//        FutureExample.futureExampleMain();
//        SessionCleaner.sessionCleanerMain();

        ExecutorServiceExample executorServiceExample = new ExecutorServiceExample();
        executorServiceExample.executorServiceExampleMain();
    }
}
