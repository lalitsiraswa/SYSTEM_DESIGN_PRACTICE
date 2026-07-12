package multithreading_and_concurrency;
// Problem Statement:
// Imagine you've placed an order, and the next steps involve sending three notifications:
// Send an SMS: This takes 2 seconds.
// Send an Email: This takes 3 seconds.
// Send ETA (Estimated Time of Arrival): This takes 5 seconds.

// OrderService class
class OrderService {
    // Main method simulates  placing an order and executing tasks sequentially
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Placing order...\n");
        // Send SMS and simulate the delay of 2 seconds
        sendSMS();
        System.out.println("Task 1 done.\n");
        // Send Email and simulate the delay of 3 seconds
        sendEmail();
        System.out.println("Task 2 done.\n");
        // Calculate  ETA (Estimated Time of Arrival) with a delay of 5 seconds
        String eta = calculateETA();
        System.out.println("Order placed. Estimated Time of Arrival: " + eta);
        System.out.println("Task 3 done.\n");
    }
    // Method to simulate sending SMS with a 2-second delay
    private static void sendSMS() {
        try{
            Thread.sleep(2000); // Delay of 2 seconds
            System.out.println("SMS Sent!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Method to simulate sending Email with a 3-second delay
    private static void sendEmail(){
        try{
            Thread.sleep(3000); // Delay of 3 seconds
            System.out.println("Email Sent!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Method to simulate calculating the ETA with a 5-second delay
    private static String calculateETA() {
        try {
            Thread.sleep(5000); // Delay of 5 seconds
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "25 minutes"; // Returning the calculated ETA
    }
}

// SOLUTION
// Creating a subclass of Thread to send SMS
class SMSThread extends Thread{
    @Override
    public void run(){
        try {
            Thread.sleep(3000); // 2-second delay for SMS
            System.out.println("SMS Sent using Thread.");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Creating a subclass of Thread to send Email
class EmailThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(2000); // 3-second delay for Email
            System.out.println("Email Sent using Thread.");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Creating a subclass of Thread to calculate ETA
class ETACalculationThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(5000); // 5-second delay for ETA calculation
            System.out.println("ETA Calculated using Thread. Estimated Time: 25 minutes.");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Implementing the Runnable Interface
// Implementing the Runnable interface for sending SMS
class SMSThreadRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000); // 2-second delay for SMS
            System.out.println("SMS Sent using Runnable.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Implementing the Runnable interface for sending Email
class EmailThreadRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(3000); // 3-second delay for Email
            System.out.println("Email Sent using Runnable.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Implementing the Runnable interface for calculating ETA
class ETAThreadRunnable implements Runnable {
    public void run() {
        try {
            Thread.sleep(5000); // 5-second delay for ETA calculation
            System.out.println("ETA Calculated using Runnable. Estimated Time: 25 minutes.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CreatingAndManagingThreads {
    static void main(String[] args) {
        // Initiating the order processing by calling the OrderService main method
//        try {
//            OrderService.main(args); // Call the OrderService's main method
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // SOLUTION
        // Create thread objects for SMS, Email, and ETA Calculation
        // SMSThread smsThread = new SMSThread();
        Thread smsThread = new Thread(new SMSThreadRunnable());
//        EmailThread emailThread = new EmailThread();
        Thread emailThread = new Thread(new EmailThreadRunnable());
//        ETACalculationThread etaCalculationThread = new ETACalculationThread();
        Thread etaCalculationThread = new Thread(new ETAThreadRunnable());

        System.out.println("Task Started.\n");

        // start all threads
        smsThread.start();
        System.out.println("Task 1 ongoing...");

        emailThread.start();
        System.out.println("Task 2 ongoing...");

        etaCalculationThread.start();
        System.out.println("Task 3 ongoing...");

        // Wait for all threads to finish
        try {
            smsThread.join();
            emailThread.join();
            etaCalculationThread.join();
            System.out.println("All tasks completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
