package behavioral_design_patterns;

// Problems
// 1. Huge if-else chains
// 2. Violates Open/Closed Principle
// 3. Hard to Maintain
// We have all the business ligic under same class like: STOP, PLAY, PAUSE.
// Let's say tomorrow if new state introduces we have to change out existing class that will break Open-Close Principle
class MediaPlayerContextMediaPlayer {
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

// SOLUTION

class MediaPlayerContext {
    private PlayerState state = new StoppedState();
    public void setState(PlayerState state) {
        this.state = state;
    }
    public void play() {
        state.play(this);
    }
    public void pause() {
        state.pause(this);
    }
    public void stop() {
        state.stop(this);
    }
}

interface PlayerState {
    void play(MediaPlayerContext player);
    void pause(MediaPlayerContext player);
    void stop(MediaPlayerContext player);
}

class StoppedState implements PlayerState {
    @Override
    public void play(MediaPlayerContext player) {
        System.out.println("Starting Playback");
        player.setState(new PlayingState());
    }
    @Override
    public void pause(MediaPlayerContext player) {
        System.out.println("Cannot Pause");
    }
    @Override
    public void stop(MediaPlayerContext player) {
        System.out.println("Already Stopped");
    }
}

class PlayingState implements PlayerState {
    @Override
    public void play(MediaPlayerContext player) {
        System.out.println("Already Playing");
    }
    @Override
    public void pause(MediaPlayerContext player) {
        System.out.println("Paused");
        player.setState(new PausedState());
    }
    @Override
    public void stop(MediaPlayerContext player) {
        System.out.println("Stopped");
        player.setState(new StoppedState());
    }
}

class PausedState implements PlayerState {
    @Override
    public void play(MediaPlayerContext player) {
        System.out.println("Resuming Playback");
        player.setState(new PlayingState());
    }
    @Override
    public void pause(MediaPlayerContext player) {
        System.out.println("Already Paused");
    }
    @Override
    public void stop(MediaPlayerContext player) {
        System.out.println("Stopped");
        player.setState(new StoppedState());
    }
}

public class StateDesignPatternChatGpt {
    static void main() {
        MediaPlayerContext player = new MediaPlayerContext();
        player.play();
        player.pause();
        player.play();
        player.stop();
    }
}
