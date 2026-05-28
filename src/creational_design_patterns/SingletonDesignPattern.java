package creational_design_patterns;

class JudgeAnalytics{
    private int run = 0;
    private int submit = 0;
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
        JudgeAnalytics judgeAnalytics1 = new JudgeAnalytics();
        JudgeAnalytics judgeAnalytics2 = new JudgeAnalytics();
        System.out.println(judgeAnalytics1);
        System.out.println(judgeAnalytics2);
    }
}
