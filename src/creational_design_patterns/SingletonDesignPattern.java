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
class JudgeAnalytics{
    private static volatile JudgeAnalytics judgeAnalytics;
    private int run = 0;
    private int submit = 0;
    private JudgeAnalytics(){}
    // Critical Section
    public static JudgeAnalytics getJudgeAnalyticsInstance(){
        if(judgeAnalytics == null){
            synchronized (JudgeAnalytics.class) {
                if(judgeAnalytics == null) {
                    judgeAnalytics = new JudgeAnalytics();
                }
            }
        }
        return judgeAnalytics;
    }
    public void countRun(){
        run++;
    }
    public void countSubmit(){
        submit++;
    }
    public int getRunCount(){
        return run;
    }
    public int getSubmitCount(){
        return submit;
    }
}

public class SingletonDesignPattern {
    static void main() {
        System.out.println("Singleton Design Pattern!");
        JudgeAnalytics judgeAnalytics1 = JudgeAnalytics.getJudgeAnalyticsInstance();
        JudgeAnalytics judgeAnalytics2 = JudgeAnalytics.getJudgeAnalyticsInstance();
        System.out.println(judgeAnalytics1);
        System.out.println(judgeAnalytics2);
    }
}
