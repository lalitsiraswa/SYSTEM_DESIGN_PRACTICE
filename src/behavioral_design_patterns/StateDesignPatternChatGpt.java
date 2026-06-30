package behavioral_design_patterns;

// Problems
// 1. Huge if-else chains
// 2. Violates Open/Closed Principle
// 3. Hard to Maintain
// We have all the business ligic under same class like: STOP, PLAY, PAUSE.
// Let's say tomorrow if new state introduces we have to change out existing class that will break Open-Close Principle
class MediaPlayer {
    private String state = "STOPPED";
    public void pressPlay() {
        if(state.equals("STOPPED"))
        {
            System.out.println("Start Playing");
            state = "PLAYING";
        }
        else if(state.equals("PLAYING"))
        {
            System.out.println("Already Playing");
        }
        else if(state.equals("PAUSED"))
        {
            System.out.println("Resume Playing");
            state = "PLAYING";
        }
    }
    public void pressPause() {
        if(state.equals("PLAYING")) {
            System.out.println("Paused");
            state = "PAUSED";
        }
        else
        {
            System.out.println("Cannot Pause");
        }
    }
    public void pressStop() {
        if(state.equals("PLAYING") || state.equals("PAUSED")) {
            System.out.println("Stopped");
            state = "STOPPED";
        }
    }
}

public class StateDesignPatternChatGpt {
    static void main() {

    }
}
