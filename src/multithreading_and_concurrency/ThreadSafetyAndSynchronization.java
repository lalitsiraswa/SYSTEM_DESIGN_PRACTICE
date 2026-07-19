package multithreading_and_concurrency;

// Race Condition

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

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

// volatile Keyword
// The volatile keyword in Java is used to ensure visibility, not atomicity. It tells the JVM that a variable's value may be
// updated by multiple threads and that every read or write should go directly to and from main memory, rather than being cached in a thread’s local memory (CPU cache).
// What it Guarantees:
// 1. visibility
// Any update made to a volatile variable by one thread becomes immediately visible to all other threads.
// Without volatile: one thread might keep reading an old value from its local cache.
// With volatile: all threads will always see the latest value in memory.
// 2. No Caching
// A volatile variable is always read from and written to main memory. This ensures there’s no outdated copy sitting in a CPU
// register or thread-local cache.
// 3. Not Atomic
// Even though volatile ensures visibility, it does not make operations atomic. For example, the following is not thread-safe
// even if count is declared as volatile: This is because count++ involves three steps (read, modify, write), and volatile cannot
// stop two threads from performing these steps simultaneously, leading to race conditions.

// Below code is not thread safe.
// You will get inconsistent results
class VolatileExample{
    volatile int count = 0;
    public void volatileExampleMain() throws InterruptedException {
        Runnable task = () -> {
            for(int i = 1; i <= 2000; i++){
                count++;
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // Result will be inconsistent
        System.out.println("COUNT: " + count);
    }
}

// Atomic Variables:-
// Java provides a set of classes under the java.util.concurrent.atomic package, designed to handle common types like integers
// and booleans in a thread-safe, high-performance way - without using locks.
// Real-Life Analogy: Social Media Likes
// Think of a "Like" counter on a popular Instagram post. Millions of users can tap the Like button at the same time.
// The system needs to safely update the like count without missing any, and it must do this without slowing everyone down with locks.
// Atomic variables work the same way - offering safe, fast updates even under heavy traffic.
// Common Atomic Classes
// AtomicInteger: for atomic operations on integers
// AtomicBoolean: for managing flags safely
// (There are also AtomicLong, AtomicReference, etc., for more advanced use cases.)
// How Atomic Variables Work
// All atomic classes use a technique called Compare-And-Swap (CAS) at the hardware level. This is what makes them lock-free
// and highly performant.
// CAS Concept:-
// CAS stands for Compare-And-Swap. It is a low-level CPU/hardware instruction that checks if a memory location holds an expected value,
// and if so, swaps it with a new value - all in one atomic step. Here's how it works in simple terms:
// “If the current value is what I expect it to be, update it with a new value. Otherwise, try again.”
// This way, threads don't block each other-they simply keep retrying until they succeed, avoiding race conditions without using locks.

class PurchaseAtomicCounter{
    // A thread-safe integer backed by hardware-level CAS(Compare-And-Swap)
    private final AtomicInteger likes = new AtomicInteger();
    // Atomically add 1 to the like counter
    public void incrementLikes(){
        int prev, next;
        do{
            // Step 1  – read the current value.
            // (Maybe outdated if another thread wins the race.)
            prev = likes.get();
            // Step 2  – compute the desired next value.
            next = prev + 1;
            // Step 3  – attempt to swap:
            /*          “If current value(likes variable value) is still ‘prev’, set it to ‘next’.”
             *          Returns true on success, false if another thread
             *          already changed the value (retry needed).
             */
        } while (!likes.compareAndSet(prev, next));
    }
    // Read-only accessor
    public int getCount() {
        return likes.get();
    }
    public void purchaseAtomicCounterMain() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName());
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable task = () ->{
            System.out.println(Thread.currentThread().getName());
            for(int i = 1; i <= 1000; i++){
                incrementLikes();
            }
        };
        Future<?> f1 = executorService.submit(task);
        Future<?> f2 = executorService.submit(task);
        Future<?> f3 = executorService.submit(task);
        Future<?> f4 = executorService.submit(task);
        Future<?> f5 = executorService.submit(task);
        // get() waits until the task finishes.
        // comment out all the .get() method and check result.
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        f5.get();
        executorService.shutdown();
        System.out.println(getCount());
    }
}

public class ThreadSafetyAndSynchronization {
    static void main() throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName());
//        RaceConditionDemo.raceConditionDemoMain();
//        FixRaceConditionDemo.fixRaceConditionDemoMain();

//        VolatileExample volatileExample = new VolatileExample();
//        volatileExample.volatileExampleMain();

        PurchaseAtomicCounter purchaseAtomicCounter = new PurchaseAtomicCounter();
        purchaseAtomicCounter.purchaseAtomicCounterMain();
    }
}
