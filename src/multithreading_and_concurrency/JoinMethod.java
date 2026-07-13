package multithreading_and_concurrency;

// The join() method causes the calling thread to wait until the thread on which join() is invoked has finished executing.
// The most important part of the definition is:
// The calling thread waits.

// Many beginners think:
// "join() pauses the worker thread."
// ❌ Wrong. It pauses the thread that calls join().

// This is the most important concept.
// join() never pauses the worker.
// It pauses whoever called join().

public class JoinMethod {
    static void main() throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker started");
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Worker finished");
        });
        worker.start();
        worker.join();
        System.out.println("Main finished");
    }
}
