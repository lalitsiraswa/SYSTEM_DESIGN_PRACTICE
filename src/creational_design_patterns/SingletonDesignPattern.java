package creational_design_patterns;

// Eager Loading - Object initialization happens at the class loading level
// Thread Safe
//class JudgeAnalytics{
//    private static final JudgeAnalytics JUDGE_ANALYTICS = new JudgeAnalytics();
//    private int run = 0;
//    private int submit = 0;
//    private JudgeAnalytics(){}
//    public static JudgeAnalytics getJudgeAnalyticsInstance(){
//        return JUDGE_ANALYTICS;
//    }
//    public void countRun(){
//        run++;
//    }
//    public void countSubmit(){
//        submit++;
//    }
//    public int getRunCount(){
//        return run;
//    }
//    public int getSubmitCount(){
//        return submit;
//    }
//}

// Lazy Loading - instance is created during the execution time.
// Thread Un-Safe
// synchronized keyword - A mechanism that controls the access of multiple threads to shared resources.
// It ensures that only one thread can access a "critical section" of code at a time, preventing data corruption and race conditions.
//class JudgeAnalytics{
//    private static JudgeAnalytics judgeAnalytics;
//    private int run = 0;
//    private int submit = 0;
//    private JudgeAnalytics(){}
//    // Critical Section
//    public static synchronized JudgeAnalytics getJudgeAnalyticsInstance(){
//        if(judgeAnalytics == null){
//            judgeAnalytics = new JudgeAnalytics();
//        }
//        return judgeAnalytics;
//    }
//    public void countRun(){
//        run++;
//    }
//    public void countSubmit(){
//        submit++;
//    }
//    public int getRunCount(){
//        return run;
//    }
//    public int getSubmitCount(){
//        return submit;
//    }
//}

// Double - Checked Locking
// It uses the volatile keyword to ensure that changes to the instance variable are immediately visible to other threads.
//class JudgeAnalytics{
//    private static volatile JudgeAnalytics judgeAnalytics;
//    private int run = 0;
//    private int submit = 0;
//    private JudgeAnalytics(){}
//    // Critical Section
//    public static JudgeAnalytics getJudgeAnalyticsInstance(){
//        if(judgeAnalytics == null){
//            synchronized (JudgeAnalytics.class) {
//                if(judgeAnalytics == null) {
//                    judgeAnalytics = new JudgeAnalytics();
//                }
//            }
//        }
//        return judgeAnalytics;
//    }
//    public void countRun(){
//        run++;
//    }
//    public void countSubmit(){
//        submit++;
//    }
//    public int getRunCount(){
//        return run;
//    }
//    public int getSubmitCount(){
//        return submit;
//    }
//}

// Bill Pugh Singleton
//class JudgeAnalytics{
//    private int run = 0;
//    private int submit = 0;
//    private JudgeAnalytics(){}
//    private static class Holder{
//        private static final JudgeAnalytics JUDGE_ANALYTICS = new JudgeAnalytics();
//    }
//    public static JudgeAnalytics getJudgeAnalyticsInstance(){
//        return Holder.JUDGE_ANALYTICS;
//    }
//    public void countRun(){
//        run++;
//    }
//    public void countSubmit(){
//        submit++;
//    }
//    public int getRunCount(){
//        return run;
//    }
//    public int getSubmitCount(){
//        return submit;
//    }
//}

// Enum Singleton
// When JVM loads:
//enum CompilerStats {
//    INSTANCE;
//}
// Java internally creates:
// public static final CompilerStats INSTANCE = new CompilerStats();
// Can Enum Have Constructor? Yes But constructor is always: private internally
enum CompilerStats{
    INSTANCE;

    private int runCount = 0;
    private int submitCount = 0;

    public void incrementRun(){
        runCount++;
    }
    public void incrementSubmit(){
        submitCount++;
    }
    public void displayStats(){
        System.out.println("RUN Count:    " + runCount);
        System.out.println("SUBMIT Count: " + submitCount);
    }
}
public class SingletonDesignPattern {
    static void main() {
        System.out.println("Singleton Design Pattern!");
        CompilerStats stats1= CompilerStats.INSTANCE;
        stats1.incrementRun();
        stats1.incrementRun();
        stats1.incrementSubmit();

        CompilerStats stats2 = CompilerStats.INSTANCE;
        stats2.displayStats();
    }
}
