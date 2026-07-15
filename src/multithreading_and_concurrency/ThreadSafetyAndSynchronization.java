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

// Synchronized Keyword:
// 1. Synchronized Method:
// The method implicitly locks on this (the current object).
// Only one thread can run increment().
// Drawback:
// Sometimes, using the synchronized keyword on an entire method can be too restrictive, especially when only a small part of
// the method actually deals with shared data.
class PurchaseCounterSyncMethod{
    private int count = 0;
    // Entire method is protected by the instance’s monitor lock
    public synchronized void increment() {
        count++;          // atomic under the lock
    }
    public synchronized int getCount() {
        return count;
    }
}

// 2. Synchronized block:
// You pick the exact section to lock, improving performance.
// To do this, you use a separate object (often named lock) just for synchronization.
// This object can be any normal Java object - commonly created once and reused inside the class to keep things organized and safe.
// Using a synchronized block allows you to define exactly which part of the code should be protected,
// instead of locking the entire method.
class PurchaseCounterSyncBlock{
    // private final Object lock = new Object();
    private int count = 0;
    public void increment() {
        // Lock only the code that truly needs protection
        synchronized (this) {
            count++;
        }
    }
    public int getCount() {
        // No lock needed for simple read, or use block if strict consistency required
        return count;
    }
}

class FixRaceConditionDemo{
    static void fixRaceConditionDemoMain() throws InterruptedException {
        // Creat a shared counter
        // PurchaseCounterSyncMethod counter = new PurchaseCounterSyncMethod();
        PurchaseCounterSyncBlock counter = new PurchaseCounterSyncBlock();
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

// Monitor Locks:-
// At the core of synchronization in Java lies the concept of Monitor Locks, which manage access by allowing only one thread to
// execute a synchronized section at any given time. Let’s take a closer look at how Monitor Locks work through the given key points.
// Key Points
// One lock per object (plus one per Class for static sync).
// Re-entrant: The same thread can enter the lock multiple times without deadlocking itself.
// Queueing: If a lock is taken, other threads wait (block) until it’s released.
// Scope matters:
// > synchronized instance method → lock on this.
// > synchronized static method → lock on the Class object.
// > synchronized (obj) block → lock on obj.
// Drawback:-
// Using monitor locks via synchronized removes data races, but over-locking can slow your app or cause deadlocks if locks
// are acquired in inconsistent order.
// While monitor locks help manage exclusive access, they also introduce overhead by forcing threads to wait,
// especially when only visibility of changes - not atomicity - is needed.
// In such cases, where synchronization might be too heavy, Java provides a lighter option: the volatile keyword.

public class ThreadSafetyAndSynchronization {
    static void main() throws InterruptedException {
//        RaceConditionDemo.raceConditionDemoMain();
        FixRaceConditionDemo.fixRaceConditionDemoMain();
    }
}
