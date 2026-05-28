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
class JudgeAnalytics{
    private static JudgeAnalytics judgeAnalytics;
    private int run = 0;
    private int submit = 0;
    private JudgeAnalytics(){}
    public static JudgeAnalytics getJudgeAnalyticsInstance(){
        if(judgeAnalytics == null){
            judgeAnalytics = new JudgeAnalytics();
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
