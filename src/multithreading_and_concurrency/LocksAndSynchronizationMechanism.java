package multithreading_and_concurrency;

// In Java, synchronized (intrinsic lock/monitor) and Lock (from java.util.concurrent.locks) are the two main ways to achieve
// mutual exclusion. When people say "mutex" in Java, they usually mean any mechanism that ensures only one thread can access
// a critical section at a time. A Java Lock is essentially an explicit mutex implementation.

// Book My Show (BMS):-
// Problem Statement
// Let's consider a real-world scenario from BookMyShow. Suppose a user begins booking a movie ticket, and this booking operation
// runs inside a thread. However, midway through the booking process, the user goes idle - maybe they switch tabs or walk away
// from the screen.
// Now, if this operation is guarded using the synchronized keyword in Java, the thread will continue holding the lock for
// the entire duration. Other users trying to access the same seat will be blocked indefinitely until that thread completes.
// This becomes a serious bottleneck in systems that expect real-time performance and responsiveness.
// Why not just rely on synchronized?
// - No timeout support: the lock waits forever.
// - No explicit control over acquiring/releasing the lock.
// - You can't interrupt a thread stuck waiting for a lock.
// - No guarantee of fairness: Some threads may wait longer than others.
// Such limitations make synchronized unsuitable for modern concurrent applications where responsiveness and control are essential.
// To solve this issue, a better alternative like a Mutex can be used.

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class TicketBooking {
    // Number of seats available
    private int availableSeats = 3;
    // Dedicated lock for this shared resource
    private final ReentrantLock lock = new ReentrantLock();
    // Public method to book a ticket
    public void bookTicket(String user) {
        System.out.println(user + " is trying to book...");
        // Acquire the lock – blocks until available
        lock.lock();
        try {
            System.out.println(user + " acquired lock.");
            // Critical section: check and update shared state
            if (availableSeats > 0) {
                System.out.println(user + " successfully booked the ticket.");
                availableSeats--;
            } else {
                System.out.println(user + " could not book the ticket. No seats left.");
            }
        } finally {
            // Always release the lock in a finally block
            System.out.println(user + " is releasing the lock.");
            lock.unlock(); // Must always execute (hence the finally block) to avoid deadlocks.
        }
    }
}

// Booking system that waits up to 2 s for a lock
class TicketBookingTryLock {
    // shared resource: seat count
    private int availableSeats = 5;
    // exclusive lock protecting seat updates
    private final ReentrantLock lock = new ReentrantLock();
    // attempts to book a ticket for the given user
    public void bookTicket(String user) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        System.out.println(user + " is trying to book at time: " + formattedTime);
        // remember whether we actually got the lock
        boolean lockAcquired = false;
        try {
            // wait up to 2 s before giving up
            lockAcquired = lock.tryLock(5, TimeUnit.SECONDS);
            if (lockAcquired) {
                System.out.println(user + " acquired lock.");
                // critical section – safe to inspect/update seats
                if (availableSeats > 0) {
                    System.out.println(user + " successfully booked the ticket.");
                    availableSeats--;
                } else {
                    System.out.println(user + " could not book the ticket. No seats left.");
                }
                // simulate a long operation that holds the lock
                // this helps demonstrate the timeout behavior for the next user
                Thread.sleep(5000);
            } else {
                currentTime = LocalTime.now();
                formattedTime = currentTime.format(formatter);
                System.out.println(user + " could not acquire lock. Try again at time: " + formattedTime);
            }
        }
        catch (InterruptedException e) {
            // restore interrupt status and log
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        finally {
            // release only if we were the owner
            if (lockAcquired) {
                System.out.println(user + " is releasing the lock.");
                lock.unlock();
            }
        }
    }
}

public class LocksAndSynchronizationMechanism {
    static void main() throws ExecutionException, InterruptedException {
//        TicketBooking booking = new TicketBooking();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Future<?> f1 = executorService.submit(() -> { booking.bookTicket("Lalit"); });
//        Future<?> f2 = executorService.submit(() -> { booking.bookTicket("Lavik"); });
//        Future<?> f3 = executorService.submit(() -> { booking.bookTicket("Lucky"); });
//        Future<?> f4 = executorService.submit(() -> { booking.bookTicket("Sonu"); });
//        Future<?> f5 = executorService.submit(() -> { booking.bookTicket("Jyoti"); });
//        f1.get();
//        f2.get();
//        f3.get();
//        f4.get();
//        f5.get();
//        executorService.shutdown();

        // ------------
        TicketBookingTryLock ticketBookingTryLock = new TicketBookingTryLock();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> f1 = executorService.submit(() -> { ticketBookingTryLock.bookTicket("Lalit"); });
        Future<?> f2 = executorService.submit(() -> { ticketBookingTryLock.bookTicket("Lavik"); });
//        Future<?> f3 = executorService.submit(() -> { ticketBookingTryLock.bookTicket("Lucky"); });
//        Future<?> f4 = executorService.submit(() -> { ticketBookingTryLock.bookTicket("Sonu"); });
//        Future<?> f5 = executorService.submit(() -> { ticketBookingTryLock.bookTicket("Jyoti"); });
        f1.get();
        f2.get();
//        f3.get();
//        f4.get();
//        f5.get();
        executorService.shutdown();
    }
}
