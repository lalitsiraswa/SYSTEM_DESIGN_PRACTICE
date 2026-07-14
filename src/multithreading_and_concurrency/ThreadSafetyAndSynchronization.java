package multithreading_and_concurrency;

// Race Condition

// Purchase counter with no protection
class PurchaseCounter{
    // Shared count value
    private int count = 0;
    // Increment the counter
    public void increment(){
        // It's not a single operation it is combination of three operations
        // 1. READ current value
        // 2. INCREMENT it
        // 3. WRITE it back
        count++; // <--- not atomic, unsafe
    }
    // Fetch the current count
    public int getCount(){
        return count;
    }
}

// Demonstrates the race condition
class RaceConditionDemo{
    static void raceConditionDemoMain() throws InterruptedException {
        // Creat a shared counter
        PurchaseCounter counter = new PurchaseCounter();
        // Task that bumps the counter 1000 times
        Runnable task = () -> {
            for(int i = 1; i <= 1000; i++){
                counter.increment();
            }
        };
        // Run the same task in two threads
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getCount());
    }
}

public class ThreadSafetyAndSynchronization {
    static void main() throws InterruptedException {
        RaceConditionDemo.raceConditionDemoMain();
    }
}
