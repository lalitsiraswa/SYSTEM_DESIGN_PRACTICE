package multithreading_and_concurrency;

//                 new Thread()
//                       │
//                       ▼
//                  +---------+
//                  |   NEW   |
//                  +---------+
//                       │
//                  start()
//                       │
//                       ▼
//                 +-------------+
//                 |  RUNNABLE   |
//                 +-------------+
//                   │   │    │
//                   │   │    │
//         waiting for  waiting  sleeping
//           a lock     forever  for time
//           ▼            ▼         ▼
//      +---------+  +----------+ +---------------+
//      | BLOCKED |  | WAITING  | | TIMED_WAITING |
//      +---------+  +----------+ +---------------+
//           │            │              │
//           └────────────┴──────────────┘
//                        │
//                        ▼
//                  +-------------+
//                  |  RUNNABLE   |
//                  +-------------+
//                        │
//                    run() ends
//                        │
//                        ▼
//                 +-------------+
//                 | TERMINATED  |
//                 +-------------+

public class ThreadLifeCycle {
    static void main() throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker started.");
            try{
                System.out.println("Worker is sleeping for 3 seconds...");
                Thread.sleep(3000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Worker finished.");
        });

        // 1. NEW
        System.out.println("1. State: " + worker.getState());
        // Start thread
        worker.start();
        // 2. RUNNABLE
        System.out.println("2. State: " + worker.getState());
        // Give the worker time to enter sleep()
        Thread.sleep(500);
        System.out.println("3. State: " + Thread.currentThread().getState());
        // 3. TIMED_WAITING
        System.out.println("3. State: " + worker.getState());
        // wait for worker
        worker.join();
        // 4. TERMINATED
        System.out.println("4. State: " + worker.getState());
    }
}
